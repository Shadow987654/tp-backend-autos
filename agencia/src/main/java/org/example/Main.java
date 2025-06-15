package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.example.common.models"}) // Especificamos solo el paquete de entidades en common
public class Main 
{
    public static void main( String[] args ) {
        SpringApplication.run(Main.class, args);
        System.out.println("Aplicación de gestión de empleados iniciada");
    }
}
