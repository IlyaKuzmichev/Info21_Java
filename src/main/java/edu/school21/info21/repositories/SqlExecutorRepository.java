package edu.school21.info21.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public int executeUpdateQuery(String query) {
        try {
            return entityManager.createNativeQuery(query).executeUpdate();
        } catch (PersistenceException e) {
            throw new IllegalArgumentException(getErrorMessage(e), e);
        }
    }

    private String getErrorMessage(PersistenceException e) {
        if (e.getCause() != null) {
            return e.getCause().getMessage();
        } else {
            return "Ошибка выполнения SQL-запроса: " + e.getMessage();
        }
    }
}
