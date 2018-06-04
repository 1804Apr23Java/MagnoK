package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CodeChallengeWeek6Application {

	public static void main(String[] args) {
		SpringApplication.run(CodeChallengeWeek6Application.class, args);
	}
}
