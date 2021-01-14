package com.phase3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com")
@SpringBootApplication
public class HandlingAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandlingAuthApplication.class, args);
	}

}
