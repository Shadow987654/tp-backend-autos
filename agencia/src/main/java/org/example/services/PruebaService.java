package org.example.services;

import org.example.common.models.Interesado;
import org.example.common.models.Prueba;
import org.example.common.models.Vehiculo;
import org.example.dto.request.PruebaRequest;
import org.example.exceptions.BusinessException;
import org.example.repositories.EmpleadoRepository;
import org.example.repositories.InteresadoRepository;
import org.example.repositories.PruebaRepository;
import org.example.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class PruebaService {

    private final PruebaRepository pruebaRepository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public PruebaService(
            PruebaRepository pruebaRepository,
            InteresadoRepository interesadoRepository,
            VehiculoRepository vehiculoRepository,
            EmpleadoRepository empleadoRepository
    ) {
        this.pruebaRepository = pruebaRepository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Prueba crearPrueba(PruebaRequest request) {
        // Validar que el interesado exista
        Interesado interesado = interesadoRepository.findById(request.getIdInteresado())
                .orElseThrow(() -> new BusinessException("El interesado no existe"));

        // Validar que el interesado no esté restringido
        if (interesado.getRestringido() != null && interesado.getRestringido() == 1) {
            throw new BusinessException("El interesado está restringido para realizar pruebas");
        }

        // Validar que la licencia del interesado no esté vencida
        validarLicenciaVigente(interesado);

        // Validar que el vehículo exista
        Vehiculo vehiculo = vehiculoRepository.findById(request.getIdVehiculo())
                .orElseThrow(() -> new BusinessException("El vehículo no existe"));

        // Validar que el empleado exista
        empleadoRepository.findById(request.getIdEmpleado().longValue())
                .orElseThrow(() -> new BusinessException("El empleado no existe"));

        // Validar que el vehículo no esté siendo usado en otra prueba activa
        List<Prueba> pruebasActivas = pruebaRepository.findActivePruebasByVehiculoId(request.getIdVehiculo());
        if (!pruebasActivas.isEmpty()) {
            throw new BusinessException("El vehículo ya está siendo utilizado en otra prueba");
        }

        // Crear la prueba
        Prueba prueba = new Prueba();
        prueba.setIdInteresado(request.getIdInteresado());
        prueba.setIdVehiculo(request.getIdVehiculo());
        prueba.setIdEmpleado(request.getIdEmpleado());
        
        // Fecha y hora actual como string en formato ISO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraActual = LocalDateTime.now().format(formatter);
        
        prueba.setFechaHoraInicio(fechaHoraActual);
        // La fecha de fin será null hasta que finalice la prueba
        prueba.setFechaHoraFin(null);
        prueba.setComentarios(null);

        return pruebaRepository.save(prueba);
    }

    private void validarLicenciaVigente(Interesado interesado) {
        // Verificar si tiene licencia registrada
        if (interesado.getNroLicencia() == null || interesado.getFechaVencimientoLicencia() == null) {
            throw new BusinessException("El interesado no tiene licencia registrada");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaVencimiento = sdf.parse(interesado.getFechaVencimientoLicencia());
            Date fechaActual = new Date();

            if (fechaVencimiento.before(fechaActual)) {
                throw new BusinessException("La licencia del interesado está vencida");
            }
        } catch (ParseException e) {
            throw new BusinessException("Error al validar la fecha de vencimiento de la licencia");
        }
    }

    public List<Prueba> getPruebasActivas() {
        return pruebaRepository.findAllActivePruebas();
    }

    public Prueba finalizarPrueba(Integer idPrueba, String comentarios) {
        Prueba prueba = pruebaRepository.findById(idPrueba)
                .orElseThrow(() -> new BusinessException("La prueba no existe"));
        
        // Validar que la prueba no esté ya finalizada
        if (prueba.getFechaHoraFin() != null) {
            throw new BusinessException("La prueba ya fue finalizada anteriormente");
        }
        
        // Fecha y hora actual como string en formato ISO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraActual = LocalDateTime.now().format(formatter);
        
        prueba.setFechaHoraFin(fechaHoraActual);
        prueba.setComentarios(comentarios);
        
        return pruebaRepository.save(prueba);
    }
}
