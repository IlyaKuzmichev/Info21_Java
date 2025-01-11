package edu.school21.info21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Обрабатываем запрос к главной странице
    @GetMapping("/")
    public String index() {
        // Возвращаем имя шаблона главной страницы
        return "index";
    }
}
