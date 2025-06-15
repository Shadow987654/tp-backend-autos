package org.example.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.util.*;

@Service
public class DatabaseExplorerService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Obtiene una lista de todas las tablas en la base de datos
     */
    public List<String> getAllTables() {
        List<String> tableNames = new ArrayList<>();
        
        // Obtener todas las entidades JPA mapeadas
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        
        // Extraer nombres de tablas
        for (EntityType<?> entityType : entities) {
            Class<?> javaType = entityType.getJavaType();
            Table tableAnnotation = javaType.getAnnotation(Table.class);
            
            if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
                tableNames.add(tableAnnotation.name());
            } else {
                // Si no hay anotación @Table, usar el nombre de la entidad
                tableNames.add(entityType.getName());
            }
        }
        
        return tableNames;
    }

    /**
     * Ejecuta una consulta SQL nativa
     * @param sql La consulta SQL nativa a ejecutar
     * @return Lista de mapas con los resultados
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> executeNativeQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        
        // Extraer metadata para obtener nombres de columnas
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql);
        org.hibernate.query.Query hibernateQuery = nativeQuery.unwrap(org.hibernate.query.Query.class);
        String[] returnAliases = hibernateQuery.getReturnAliases();
        
        for (Object[] row : results) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 0; i < row.length; i++) {
                String columnName = (returnAliases != null && i < returnAliases.length) ? 
                                    returnAliases[i] : "column" + i;
                rowMap.put(columnName, row[i]);
            }
            resultMaps.add(rowMap);
        }
        
        return resultMaps;
    }
    
    /**
     * Obtiene todos los registros de una tabla
     * @param tableName El nombre de la tabla
     * @return Lista de mapas con los registros
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getAllRecords(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return executeNativeQuery(sql);
    }
}
