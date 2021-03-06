package com.kevinjanvier.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestingTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingTddApplication.class, args);
	}

}
