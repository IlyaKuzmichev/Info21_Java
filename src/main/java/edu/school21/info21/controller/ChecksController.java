package edu.school21.info21.controller;

import edu.school21.info21.dto.ChecksDTO;
import edu.school21.info21.mappers.ChecksMapper;
import edu.school21.info21.services.ChecksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/checks")
public class ChecksController {

    private final ChecksService checksService;

    @Autowired
    public ChecksController(ChecksService checksService) {
        this.checksService = checksService;
    }

    @GetMapping
    public String checks(Model model) {
        List<ChecksDTO> checksList = checksService.getAllChecks();
        model.addAttribute("checks", checksList);
        return "checks";
    }
}
