package edu.school21.info21.controller;

import edu.school21.info21.services.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataTransferController {

    private final DataTransferService dataTransferService;

    @Autowired
    public DataTransferController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToCsv(
            @RequestParam String tableName,
            @RequestParam String fileName,
            @RequestParam(required = false) String columns
    ) {
        System.out.println(columns);
        System.out.println(tableName);
        System.out.println(fileName);
        byte[] csvData = dataTransferService.exportToCsv(tableName, fileName, columns);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvData);
    }

}
