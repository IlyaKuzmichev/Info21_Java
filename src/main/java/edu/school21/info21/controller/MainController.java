package edu.school21.info21.controller;

import edu.school21.info21.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final QueryService queryService;

    public MainController(QueryService queryService) {
        this.queryService = queryService;
    }

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
        model.addAttribute("queries", queryService.getAllQueries());
        return "operations";
    }
}
