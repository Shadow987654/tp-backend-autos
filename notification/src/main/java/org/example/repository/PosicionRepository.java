package org.example.repository;

import org.example.common.models.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
    
    // Buscar posiciones en un rango de fechas
    List<Posicion> findByIdVehiculoAndFechaHoraBetweenOrderByFechaHoraAsc(
            Integer idVehiculo, String fechaInicio, String fechaFin);
}
