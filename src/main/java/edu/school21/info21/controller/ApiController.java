package edu.school21.info21.controller;

import edu.school21.info21.dto.ChecksDTO;
import edu.school21.info21.services.ChecksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "API", description = "API Gateway")
public class ApiController {

    private ChecksService checksService;

    @Autowired
    public void ChecksController(ChecksService checksService) {
        this.checksService = checksService;
    }

    @GetMapping("/checks")
    @Operation(summary = "List of checks", responses = {
            @ApiResponse(responseCode = "200", description = "Checks list")
    })
    public ResponseEntity<List<ChecksDTO>> checks() {
        return ResponseEntity.ok(checksService.getAllChecks());
    }
}
