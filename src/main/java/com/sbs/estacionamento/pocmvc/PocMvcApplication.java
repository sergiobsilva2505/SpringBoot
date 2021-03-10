package com.sbs.estacionamento.pocmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sbs.estacionamento.pocmvc.model.repo")
@EntityScan("com.sbs.estacionamento.pocmvc.model")
@SpringBootApplication
public class PocMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocMvcApplication.class, args);
	}

}
