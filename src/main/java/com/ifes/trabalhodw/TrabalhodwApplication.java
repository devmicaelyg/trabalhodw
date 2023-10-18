package com.ifes.trabalhodw;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "school",
		version = "1.0",
		description = "A Webservice about school"))
@SpringBootApplication
public class TrabalhodwApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhodwApplication.class, args);
	}

}
