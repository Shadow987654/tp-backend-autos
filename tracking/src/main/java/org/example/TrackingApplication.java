package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackingApplication {
    public static void main( String[] args ) {
        SpringApplication.run(TrackingApplication.class, args);
        System.out.println("Servicio de Tracking iniciado");
    }
}
