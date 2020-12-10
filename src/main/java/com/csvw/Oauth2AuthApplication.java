package com.csvw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lk
 */
@SpringBootApplication
public class Oauth2AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthApplication.class, args);
	}
}
