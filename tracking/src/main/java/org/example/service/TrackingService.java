package org.example.service;

import org.example.client.AgencyConfigClient;
import org.example.common.models.Notificacion;
import org.example.common.models.Posicion;
import org.example.common.models.Prueba;
import org.example.common.models.Vehiculo;
import org.example.dto.request.PosicionRequest;
import org.example.dto.response.config.AgencyConfig;
import org.example.dto.response.config.DangerZone;
import org.example.dto.response.config.Location;
import org.example.repository.NotificacionRepository;
import org.example.repository.PosicionRepository;
import org.example.repository.PruebaRepository;
import org.example.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {

    private final PosicionRepository posicionRepository;
    private final PruebaRepository pruebaRepository;
    private final VehiculoRepository vehiculoRepository;
    private final NotificacionRepository notificacionRepository;
    private final AgencyConfigClient configClient;

    @Autowired
    public TrackingService(
            PosicionRepository posicionRepository,
            PruebaRepository pruebaRepository,
            VehiculoRepository vehiculoRepository,
            NotificacionRepository notificacionRepository,
            AgencyConfigClient configClient) {
        this.posicionRepository = posicionRepository;
        this.pruebaRepository = pruebaRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.notificacionRepository = notificacionRepository;
        this.configClient = configClient;
    }

    public Posicion registrarPosicion(PosicionRequest request) {
        // 1. Buscar vehículo
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(request.getIdVehiculo());
        if (vehiculoOpt.isEmpty()) {
            throw new IllegalArgumentException("El vehículo no existe");
        }
        Vehiculo vehiculo = vehiculoOpt.get();

        // 2. Guardar la posición
        Posicion posicion = new Posicion();
        posicion.setIdVehiculo(request.getIdVehiculo());
        posicion.setLatitud(request.getLatitud());
        posicion.setLongitud(request.getLongitud());
        posicion.setFechaHora(getCurrentTimeAsString());
        
        Posicion posicionGuardada = posicionRepository.save(posicion);

        // 3. Verificar si el vehículo está en una prueba activa
        List<Prueba> pruebasActivas = pruebaRepository.findActivePruebasByVehiculoId(request.getIdVehiculo());
        if (!pruebasActivas.isEmpty()) {
            // 4. Obtener la configuración de la agencia
            AgencyConfig config = configClient.getAgencyConfig();
            
            // 5. Verificar si la posición está dentro de los límites permitidos
            boolean dentroDelRadio = estaEnRadio(request.getLatitud(), request.getLongitud(), 
                    config.getUbicacionAgencia().getLatitud(), config.getUbicacionAgencia().getLongitud(), 
                    config.getRadioMaximoMetros());
            
            if (!dentroDelRadio) {
                // 6. Crear notificación por exceder el radio máximo
                crearNotificacion(
                    pruebasActivas.get(0), 
                    "LIMITE_EXCEDIDO", 
                    "El vehículo con patente " + vehiculo.getPatente() + " ha excedido el límite permitido."
                );
            }
            
            // 7. Verificar si está en alguna zona peligrosa
            Optional<DangerZone> zonaEncontrada = estaEnZonaPeligrosa(
                    request.getLatitud(), 
                    request.getLongitud(), 
                    config.getZonasPeligrosas()
            );
            
            if (zonaEncontrada.isPresent()) {
                // 8. Crear notificación por ingresar a zona peligrosa
                crearNotificacion(
                    pruebasActivas.get(0), 
                    "ZONA_PELIGROSA", 
                    "El vehículo con patente " + vehiculo.getPatente() + 
                        " ha ingresado a una zona peligrosa: " + zonaEncontrada.get().getNombreZona()
                );
            }
        }

        return posicionGuardada;
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private boolean estaEnRadio(double lat1, double lon1, double lat2, double lon2, double radioMetros) {
        // Cálculo aproximado de la distancia usando la fórmula de Haversine
        final int R = 6371000; // Radio de la Tierra en metros
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        
        return distance <= radioMetros;
    }

    private Optional<DangerZone> estaEnZonaPeligrosa(double lat, double lon, List<DangerZone> zonas) {
        for (DangerZone zona : zonas) {
            Location coords = zona.getCoordenadas();
            if (estaEnRadio(lat, lon, coords.getLatitud(), coords.getLongitud(), zona.getRadioMetros())) {
                return Optional.of(zona);
            }
        }
        return Optional.empty();
    }

    private void crearNotificacion(Prueba prueba, String tipo, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdEmpleado(prueba.getIdEmpleado());
        notificacion.setIdPrueba(prueba.getId());
        notificacion.setTipo(tipo);
        notificacion.setMensaje(mensaje);
        notificacion.setFechaHora(getCurrentTimeAsString());
        notificacion.setEstado("ENVIADA");
        
        notificacionRepository.save(notificacion);
    }
}
