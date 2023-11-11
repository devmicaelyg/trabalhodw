package com.ifes.trabalhodw;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Projeto Origami",
		version = "1.0",
		description = "Componentes:\n- Kailany Faustino\n- Micaely Gusmão\n- Richard Lucas"))
@SpringBootApplication
public class TrabalhodwApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhodwApplication.class, args);
	}

}
