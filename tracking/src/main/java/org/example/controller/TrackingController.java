package org.example.controller;

import org.example.common.models.Posicion;
import org.example.dto.request.PosicionRequest;
import org.example.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/posicion")
    public ResponseEntity<?> registrarPosicion(@RequestBody PosicionRequest request) {
        try {
            Posicion posicionGuardada = trackingService.registrarPosicion(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(posicionGuardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error al procesar la posición: " + e.getMessage()));
        }
    }

    private static class ErrorResponse {
        private final String mensaje;
        
        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }
        
        public String getMensaje() {
            return mensaje;
        }
    }
}
