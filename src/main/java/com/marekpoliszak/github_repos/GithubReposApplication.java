package com.marekpoliszak.github_repos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.marekpoliszak.github_repos")
public class GithubReposApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubReposApplication.class, args);
	}

}
