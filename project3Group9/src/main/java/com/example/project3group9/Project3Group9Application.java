package com.example.project3group9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Project3Group9Application {
    @RequestMapping("/")
    public String home() {
        return "hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Project3Group9Application.class, args);
    }

}
