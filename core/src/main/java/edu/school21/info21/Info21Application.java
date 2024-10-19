package edu.school21.info21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.school21.info21", "edu.school21.authorization"})
@EnableMongoRepositories(basePackages = "edu.school21.authorization")
public class Info21Application {

    public static void main(String[] args) {
        SpringApplication.run(Info21Application.class, args);
    }

}
