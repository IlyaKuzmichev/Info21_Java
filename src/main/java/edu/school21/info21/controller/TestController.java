package edu.school21.info21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye";
    }
}
