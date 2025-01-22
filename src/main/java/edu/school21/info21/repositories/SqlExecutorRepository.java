package edu.school21.info21.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SqlExecutorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List executeSelectQuery(String query) {
        try {
            return entityManager.createNativeQuery(query).getResultList();
        } catch (PersistenceException e) {
            throw new IllegalArgumentException("Ошибка выполнения SELECT-запроса: " + e.getCause().getMessage(), e);
        }
    }

    public int executeUpdateQuery(String query) {
        try {
            return entityManager.createNativeQuery(query).executeUpdate();
        } catch (PersistenceException e) {
            throw new IllegalArgumentException("Ошибка выполнения запроса: " + e.getCause().getMessage(), e);
        }
    }
}
