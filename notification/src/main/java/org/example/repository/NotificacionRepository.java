package org.example.repository;

import org.example.common.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    
    // Buscar notificaciones por tipo
    List<Notificacion> findByTipoIn(List<String> tipos);
    
    // Buscar notificaciones por empleado y tipo
    List<Notificacion> findByIdEmpleadoAndTipoIn(Integer idEmpleado, List<String> tipos);
    
    // Buscar notificaciones por prueba y tipo
    List<Notificacion> findByIdPruebaAndTipoIn(Integer idPrueba, List<String> tipos);
    
    // Buscar notificaciones por estado
    List<Notificacion> findByEstadoIn(List<String> estados);
    
    // Buscar notificaciones por empleado y estado
    List<Notificacion> findByIdEmpleadoAndEstadoIn(Integer idEmpleado, List<String> estados);
    
    // Buscar notificaciones por prueba y estado
    List<Notificacion> findByIdPruebaAndEstadoIn(Integer idPrueba, List<String> estados);
}
