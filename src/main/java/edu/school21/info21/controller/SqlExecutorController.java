package edu.school21.info21.controller;

import edu.school21.info21.services.SqlExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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
        String query = request.get("query");
        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body("Запрос не может быть пустым.");
        }

        try {
            Object result = sqlExecutorService.executeSql(query);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка: " + e.getMessage());
        }
    }
}
