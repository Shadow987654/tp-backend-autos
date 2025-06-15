package org.example.controllers;

import org.example.common.models.Prueba;
import org.example.dto.request.PruebaRequest;
import org.example.dto.request.FinalizarPruebaRequest;
import org.example.dto.response.PruebaResponse;
import org.example.exceptions.BusinessException;
import org.example.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    private final PruebaService pruebaService;

    @Autowired
    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @PostMapping
    public ResponseEntity<?> crearPrueba(@RequestBody PruebaRequest request) {
        try {
            Prueba nuevaPrueba = pruebaService.crearPrueba(request);
            PruebaResponse response = PruebaResponse.fromEntity(nuevaPrueba);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error al crear la prueba: " + e.getMessage()));
        }
    }

    /**
     * Endpoint para listar todas las pruebas activas/en curso
     * @return Lista de pruebas que no han sido finalizadas
     */
    @GetMapping("/activas")
    public ResponseEntity<List<PruebaResponse>> getPruebasActivas() {
        List<Prueba> pruebas = pruebaService.getPruebasActivas();
        List<PruebaResponse> responses = pruebas.stream()
                .map(PruebaResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    
    /**
     * Finaliza una prueba en curso y permite agregar comentarios
     * @param id ID de la prueba a finalizar
     * @param request Objeto con los comentarios
     * @return Detalles de la prueba finalizada
     */
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarPrueba(
            @PathVariable("id") Integer id,
            @RequestBody FinalizarPruebaRequest request) {
        try {
            Prueba pruebaFinalizada = pruebaService.finalizarPrueba(id, request.getComentarios());
            PruebaResponse response = PruebaResponse.fromEntity(pruebaFinalizada);
            return ResponseEntity.ok(response);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error al finalizar la prueba: " + e.getMessage()));
        }
    }
    
    // Clase interna para manejar errores
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
