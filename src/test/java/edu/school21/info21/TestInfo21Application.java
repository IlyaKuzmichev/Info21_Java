package edu.school21.info21;

import org.springframework.boot.SpringApplication;

public class TestInfo21Application {

    public static void main(String[] args) {
        SpringApplication.from(Info21Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
