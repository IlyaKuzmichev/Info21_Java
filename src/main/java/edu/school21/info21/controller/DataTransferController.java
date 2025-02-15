package edu.school21.info21.controller;

import edu.school21.info21.services.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
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
        byte[] csvData = dataTransferService.exportToCsv(tableName, fileName, columns);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvData);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importFromCsv(
            @RequestParam String tableName,
            @RequestParam(required = false) String columns,
            @RequestParam("file") MultipartFile file,
            Model model
    ) {
        try {
            dataTransferService.importFromCsv(tableName, columns, file);
            return "redirect:/" + tableName;
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка импорта: " + e.getMessage());
            return "error";
        }
    }


}
