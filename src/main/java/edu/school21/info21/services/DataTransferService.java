package edu.school21.info21.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class DataTransferService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public byte[] exportToCsv(String tableName, String fileName, String columns) {
        String filePath = "/shared/" + fileName;
        String fileAbsPath = "." + filePath;
        String tableNameWithSchema = "app." + tableName;
        try {
            // Вызов процедуры
            String sql = "CALL app.export_to_csv(:tbl, :filename, :columns)";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("tbl", tableNameWithSchema);
            query.setParameter("filename", filePath);
            query.setParameter("columns", columns != null ? columns : "");

            // Выполнение запроса
            query.executeUpdate();

            // Чтение файла
            File file = new File(fileAbsPath);
            if (!file.exists()) {
                throw new RuntimeException("Файл не был создан: " + filePath);
            }

            // Возврат содержимого файла как массива байтов
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage(), e);
        } finally {
            // Удаление файла после его прочтения
            File file = new File(fileAbsPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
