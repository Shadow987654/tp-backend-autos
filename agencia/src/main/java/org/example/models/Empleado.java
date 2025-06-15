package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Opción 1: Cambiar el nombre de la entidad
@Entity(name = "EmpleadosAgencia") 
@Data
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEGAJO", nullable = false)
    private Integer legajo;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private Double telefono;
}

// Opción 2: Convertir esta clase en un DTO no persistente (mejor solución)
// Eliminar la anotación @Entity y cualquier otra anotación JPA
// @Data
// @NoArgsConstructor
// public class EmpleadoDTO {
//     // ... existing code ...
// }