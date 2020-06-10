package com.vanhalen.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vanhalen.filters", "com.vanhalen.config", "com.vanhalen.logic", "com.vanhalen.endpoints", "com.vanhalen.messaging"})
@EntityScan({"com.vanhalen.domain"})
@EnableJpaRepositories({"com.vanhalen.repositories"})
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
