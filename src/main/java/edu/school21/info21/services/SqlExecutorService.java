package edu.school21.info21.services;

import edu.school21.info21.repositories.SqlExecutorRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SqlExecutorService {

    private final SqlExecutorRepository sqlExecutorRepository;

    @Autowired
    public SqlExecutorService(SqlExecutorRepository sqlExecutorRepository) {
        this.sqlExecutorRepository = sqlExecutorRepository;
    }

    public Object executeSql(String query) {
        query = query.trim();

        try {
            if (query.toLowerCase().startsWith("select")) {
                return sqlExecutorRepository.executeSelectQuery(query);
            } else if (query.toLowerCase().startsWith("insert") ||
                    query.toLowerCase().startsWith("update") ||
                    query.toLowerCase().startsWith("delete")) {
                int affectedRows = sqlExecutorRepository.executeUpdateQuery(query);
                return Map.of("message", "Запрос выполнен успешно.", "affectedRows", affectedRows);
                //return "Запрос выполнен успешно. Затронуто строк: " + affectedRows;
            } else {
                throw new IllegalArgumentException("Неподдерживаемый тип SQL-запроса. Разрешены только SELECT, INSERT, UPDATE, DELETE.");
            }
        } catch (PersistenceException e) {
            // Перехват ошибок JPA/SQL и преобразование их в понятный вид
            throw new IllegalArgumentException(e.getCause().getMessage(), e);
        }
    }
}
