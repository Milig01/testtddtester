package org.example.testtddtester;

import org.springframework.boot.SpringApplication;

public class TestTestTddTesterApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestTddTesterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
