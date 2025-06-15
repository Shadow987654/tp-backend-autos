package org.example.repositories;

import org.example.common.models.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {
    
    // Buscar pruebas activas para un vehículo específico
    @Query("SELECT p FROM Pruebas p WHERE p.idVehiculo = :idVehiculo AND p.fechaHoraFin IS NULL")
    List<Prueba> findActivePruebasByVehiculoId(@Param("idVehiculo") Integer idVehiculo);
    
    // Buscar todas las pruebas activas
    @Query("SELECT p FROM Pruebas p WHERE p.fechaHoraFin IS NULL")
    List<Prueba> findAllActivePruebas();
}
