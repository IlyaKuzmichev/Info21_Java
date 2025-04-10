package edu.school21.info21.controller;

import edu.school21.info21.services.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    private final QueryService queryService;

    public MainController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/")
    public String homePage() {
        log.info("Пользователь зашел на главную страницу");
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        log.info("Пользователь зашел на страницу о проекте");
        return "about";
    }

    @GetMapping("/data")
    public String dataPage() {
        log.info("Пользователь зашел на страницу с данными");
        return "data";
    }

    @GetMapping("/operations")
    public String operationsPage(Model model) {
        log.info("Пользователь зашел на страницу с операциями");
        model.addAttribute("queries", queryService.getAllQueries());
        return "operations";
    }
}
