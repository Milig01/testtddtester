package org.example.testtddtester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestTddTesterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestTddTesterApplication.class, args);
    }

}