package edu.school21.info21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/data")
    public String dataPage() {
        return "data";
    }

    @GetMapping("/operations")
    public String operationsPage(Model model) {
        return "operations";
    }
}
