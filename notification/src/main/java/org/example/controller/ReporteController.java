package org.example.controller;

import org.example.dto.request.FechaRangoRequest;
import org.example.dto.response.IncidenteReporte;
import org.example.dto.response.PruebaReporteDetalle;
import org.example.dto.response.VehiculoKilometrosReporte;
import org.example.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;
    
    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
    
    /**
     * f.i. Reporte de todos los incidentes (límites excedidos y zonas peligrosas)
     */
    @GetMapping("/incidentes")
    public ResponseEntity<List<IncidenteReporte>> getReporteIncidentes() {
        List<IncidenteReporte> reportes = reporteService.getReporteIncidentes();
        return ResponseEntity.ok(reportes);
    }
    
    /**
     * f.ii. Detalle de incidentes para un empleado
     */
    @GetMapping("/incidentes/empleado/{idEmpleado}")
    public ResponseEntity<List<IncidenteReporte>> getReporteIncidentesPorEmpleado(
            @PathVariable Integer idEmpleado) {
        List<IncidenteReporte> reportes = reporteService.getReporteIncidentesPorEmpleado(idEmpleado);
        return ResponseEntity.ok(reportes);
    }
    
    /**
     * f.iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un período determinado
     */
    @PostMapping("/vehiculos/{idVehiculo}/kilometros")
    public ResponseEntity<VehiculoKilometrosReporte> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestBody FechaRangoRequest rangoFechas) {
        try {
            VehiculoKilometrosReporte reporte = reporteService.getKilometrosVehiculoPorPeriodo(
                    idVehiculo, rangoFechas);
            return ResponseEntity.ok(reporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * f.iv. Detalle de pruebas realizadas para un vehículo
     */
    @GetMapping("/vehiculos/{idVehiculo}/pruebas")
    public ResponseEntity<List<PruebaReporteDetalle>> getPruebasVehiculo(
            @PathVariable Integer idVehiculo) {
        try {
            List<PruebaReporteDetalle> reportes = reporteService.getPruebasDetalleVehiculo(idVehiculo);
            return ResponseEntity.ok(reportes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
