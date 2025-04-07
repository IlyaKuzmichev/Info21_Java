package edu.school21.info21.controller;

import edu.school21.info21.services.SqlExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/execute-sql")
public class SqlExecutorController {

    private final SqlExecutorService sqlExecutorService;

    @Autowired
    public SqlExecutorController(SqlExecutorService sqlExecutorService) {
        this.sqlExecutorService = sqlExecutorService;
    }

    @PostMapping
    public ResponseEntity<?> executeSql(@RequestBody Map<String, String> request) {
        log.info("Пользователь запрашивает выполнение SQL запроса");
        String query = request.get("query");
        if (query == null || query.isEmpty()) {
            log.error("Запрос не может быть пустым.");
            return ResponseEntity.badRequest().body("Запрос не может быть пустым.");
        }

        try {
            log.info("Выполняется запрос: {}", query);
            Object result = sqlExecutorService.executeSql(query);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("Ошибка выполнения запроса: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка выполнения запроса: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка: " + e.getMessage());
        }
    }
}
