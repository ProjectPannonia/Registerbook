package com.registerbook.registerbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.registerbook.registerbook.repository")
@EntityScan("com.registerbook.registerbook.model")
@SpringBootApplication
public class RegisterbookApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisterbookApplication.class, args);
	}
}
