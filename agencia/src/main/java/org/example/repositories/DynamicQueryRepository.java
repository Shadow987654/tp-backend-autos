package org.example.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DynamicQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Construye una consulta dinámica basada en parámetros
     * @param entityClass La clase de entidad
     * @param params Mapa de parámetros para filtrar
     * @return Lista de resultados
     */
    public <T> List<T> findByParameters(Class<T> entityClass, Map<String, Object> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        
        List<Predicate> predicates = new ArrayList<>();
        
        // Construir predicados basados en los parámetros
        params.forEach((key, value) -> {
            if (value != null) {
                predicates.add(cb.equal(root.get(key), value));
            }
        });
        
        cq.where(predicates.toArray(new Predicate[0]));
        
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
    
    /**
     * Ejecuta una consulta JPQL personalizada
     * @param jpql La consulta JPQL
     * @param params Parámetros con nombre
     * @param entityClass La clase de resultado esperada
     * @return Lista de resultados
     */
    public <T> List<T> executeJpqlQuery(String jpql, Map<String, Object> params, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
        
        // Aplicar parámetros
        params.forEach(query::setParameter);
        
        return query.getResultList();
    }
}
