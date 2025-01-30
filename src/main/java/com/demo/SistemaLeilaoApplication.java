package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaLeilaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaLeilaoApplication.class, args);
	}
}
