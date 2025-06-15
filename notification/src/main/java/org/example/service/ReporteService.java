package org.example.service;

import org.example.common.models.Notificacion;
import org.example.common.models.Posicion;
import org.example.common.models.Prueba;
import org.example.common.models.Vehiculo;
import org.example.dto.request.FechaRangoRequest;
import org.example.dto.response.IncidenteReporte;
import org.example.dto.response.PruebaReporteDetalle;
import org.example.dto.response.VehiculoKilometrosReporte;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final NotificacionRepository notificacionRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final VehiculoRepository vehiculoRepository;
    
    @Autowired
    public ReporteService(
            NotificacionRepository notificacionRepository,
            PruebaRepository pruebaRepository,
            PosicionRepository posicionRepository,
            VehiculoRepository vehiculoRepository) {
        this.notificacionRepository = notificacionRepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    /**
     * f.i. Reporte de todos los incidentes (límites excedidos y zonas peligrosas)
     */
    public List<IncidenteReporte> getReporteIncidentes() {
        // Buscar todas las notificaciones de tipo LIMITE_EXCEDIDO o ZONA_PELIGROSA
        List<Notificacion> incidentes = notificacionRepository.findByTipoIn(
                List.of("LIMITE_EXCEDIDO", "ZONA_PELIGROSA"));
        
        // Agrupar por prueba
        Map<Integer, List<Notificacion>> incidentesPorPrueba = incidentes.stream()
                .collect(Collectors.groupingBy(Notificacion::getIdPrueba));
        
        // Crear reporte para cada prueba
        List<IncidenteReporte> reportes = new ArrayList<>();
        incidentesPorPrueba.forEach((idPrueba, notificacionesList) -> {
            if (idPrueba != null) {
                Optional<Prueba> pruebaOpt = pruebaRepository.findById(idPrueba);
                if (pruebaOpt.isPresent()) {
                    Prueba prueba = pruebaOpt.get();
                    IncidenteReporte reporte = new IncidenteReporte();
                    reporte.setIdPrueba(prueba.getId());
                    reporte.setFechaPrueba(prueba.getFechaHoraInicio());
                    
                    // Obtener datos relacionados
                    if (prueba.getVehiculo() != null) {
                        reporte.setPatenteVehiculo(prueba.getVehiculo().getPatente());
                    }
                    
                    if (prueba.getEmpleado() != null) {
                        reporte.setNombreEmpleado(
                            prueba.getEmpleado().getNombre() + " " + prueba.getEmpleado().getApellido());
                    }
                    
                    if (prueba.getInteresado() != null) {
                        reporte.setNombreInteresado(
                            prueba.getInteresado().getNombre() + " " + prueba.getInteresado().getApellido());
                    }
                    
                    // Crear detalles de incidentes
                    List<IncidenteReporte.IncidenteDetalle> detalles = notificacionesList.stream()
                            .map(notif -> {
                                IncidenteReporte.IncidenteDetalle detalle = new IncidenteReporte.IncidenteDetalle();
                                detalle.setTipo(notif.getTipo());
                                detalle.setFechaHora(notif.getFechaHora());
                                detalle.setMensaje(notif.getMensaje());
                                return detalle;
                            })
                            .collect(Collectors.toList());
                    
                    reporte.setIncidentes(detalles);
                    reportes.add(reporte);
                }
            }
        });
        
        return reportes;
    }

    /**
     * f.ii. Detalle de incidentes para un empleado
     */
    public List<IncidenteReporte> getReporteIncidentesPorEmpleado(Integer idEmpleado) {
        // Buscar todas las notificaciones del empleado de tipo LIMITE_EXCEDIDO o ZONA_PELIGROSA
        List<Notificacion> incidentes = notificacionRepository.findByIdEmpleadoAndTipoIn(
                idEmpleado, List.of("LIMITE_EXCEDIDO", "ZONA_PELIGROSA"));
        
        // Usar la misma lógica de agrupación que en getReporteIncidentes
        Map<Integer, List<Notificacion>> incidentesPorPrueba = incidentes.stream()
                .collect(Collectors.groupingBy(Notificacion::getIdPrueba));
        
        // Crear reporte para cada prueba
        List<IncidenteReporte> reportes = new ArrayList<>();
        incidentesPorPrueba.forEach((idPrueba, notificacionesList) -> {
            if (idPrueba != null) {
                Optional<Prueba> pruebaOpt = pruebaRepository.findById(idPrueba);
                if (pruebaOpt.isPresent()) {
                    Prueba prueba = pruebaOpt.get();
                    IncidenteReporte reporte = new IncidenteReporte();
                    reporte.setIdPrueba(prueba.getId());
                    reporte.setFechaPrueba(prueba.getFechaHoraInicio());
                    
                    // Obtener datos relacionados
                    if (prueba.getVehiculo() != null) {
                        reporte.setPatenteVehiculo(prueba.getVehiculo().getPatente());
                    }
                    
                    if (prueba.getEmpleado() != null) {
                        reporte.setNombreEmpleado(
                            prueba.getEmpleado().getNombre() + " " + prueba.getEmpleado().getApellido());
                    }
                    
                    if (prueba.getInteresado() != null) {
                        reporte.setNombreInteresado(
                            prueba.getInteresado().getNombre() + " " + prueba.getInteresado().getApellido());
                    }
                    
                    // Crear detalles de incidentes
                    List<IncidenteReporte.IncidenteDetalle> detalles = notificacionesList.stream()
                            .map(notif -> {
                                IncidenteReporte.IncidenteDetalle detalle = new IncidenteReporte.IncidenteDetalle();
                                detalle.setTipo(notif.getTipo());
                                detalle.setFechaHora(notif.getFechaHora());
                                detalle.setMensaje(notif.getMensaje());
                                return detalle;
                            })
                            .collect(Collectors.toList());
                    
                    reporte.setIncidentes(detalles);
                    reportes.add(reporte);
                }
            }
        });
        
        return reportes;
    }

    /**
     * f.iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un período determinado
     */
    public VehiculoKilometrosReporte getKilometrosVehiculoPorPeriodo(
            Integer idVehiculo, FechaRangoRequest rangoFechas) {
        
        // Validar que el vehículo exista
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo no existe"));
        
        // Buscar las posiciones del vehículo en el periodo especificado
        List<Posicion> posiciones = posicionRepository.findByIdVehiculoAndFechaHoraBetweenOrderByFechaHoraAsc(
                idVehiculo, rangoFechas.getFechaInicio(), rangoFechas.getFechaFin());
        
        // Calcular distancia total
        double totalKilometros = 0;
        for (int i = 1; i < posiciones.size(); i++) {
            Posicion anterior = posiciones.get(i-1);
            Posicion actual = posiciones.get(i);
            
            totalKilometros += calcularDistancia(
                    anterior.getLatitud(), anterior.getLongitud(),
                    actual.getLatitud(), actual.getLongitud());
        }
        
        // Crear el reporte
        VehiculoKilometrosReporte reporte = new VehiculoKilometrosReporte();
        reporte.setIdVehiculo(idVehiculo);
        reporte.setPatente(vehiculo.getPatente());
        reporte.setFechaInicio(rangoFechas.getFechaInicio());
        reporte.setFechaFin(rangoFechas.getFechaFin());
        reporte.setTotalKilometros(totalKilometros);
        
        return reporte;
    }

    /**
     * f.iv. Detalle de pruebas realizadas para un vehículo
     */
    public List<PruebaReporteDetalle> getPruebasDetalleVehiculo(Integer idVehiculo) {
        // Validar que el vehículo exista
        vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo no existe"));
        
        // Buscar todas las pruebas para ese vehículo
        List<Prueba> pruebas = pruebaRepository.findByIdVehiculoOrderByFechaHoraInicioDesc(idVehiculo);
        
        // Convertir a reporte
        return pruebas.stream().map(prueba -> {
            PruebaReporteDetalle detalle = new PruebaReporteDetalle();
            detalle.setIdPrueba(prueba.getId());
            detalle.setFechaHoraInicio(prueba.getFechaHoraInicio());
            detalle.setFechaHoraFin(prueba.getFechaHoraFin());
            detalle.setComentarios(prueba.getComentarios());
            
            // Obtener datos relacionados
            if (prueba.getEmpleado() != null) {
                detalle.setNombreEmpleado(prueba.getEmpleado().getNombre() + " " + prueba.getEmpleado().getApellido());
            }
            
            if (prueba.getInteresado() != null) {
                detalle.setNombreInteresado(prueba.getInteresado().getNombre() + " " + prueba.getInteresado().getApellido());
            }
            
            // Verificar si tuvo incidentes
            List<Notificacion> incidentes = notificacionRepository.findByIdPruebaAndTipoIn(
                    prueba.getId(), List.of("LIMITE_EXCEDIDO", "ZONA_PELIGROSA"));
            detalle.setTuvoIncidentes(!incidentes.isEmpty());
            
            // Calcular distancia recorrida durante la prueba
            if (prueba.getFechaHoraInicio() != null && prueba.getFechaHoraFin() != null) {
                List<Posicion> posiciones = posicionRepository.findByIdVehiculoAndFechaHoraBetweenOrderByFechaHoraAsc(
                        idVehiculo, prueba.getFechaHoraInicio(), prueba.getFechaHoraFin());
                
                double distanciaTotal = 0;
                for (int i = 1; i < posiciones.size(); i++) {
                    Posicion anterior = posiciones.get(i-1);
                    Posicion actual = posiciones.get(i);
                    
                    distanciaTotal += calcularDistancia(
                            anterior.getLatitud(), anterior.getLongitud(),
                            actual.getLatitud(), actual.getLongitud());
                }
                detalle.setDistanciaRecorrida(distanciaTotal);
            }
            
            return detalle;
        }).collect(Collectors.toList());
    }

    /**
     * Calcula la distancia en kilómetros entre dos puntos geográficos usando la fórmula de Haversine
     */
    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la tierra en km
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c; // distancia en km
    }
}