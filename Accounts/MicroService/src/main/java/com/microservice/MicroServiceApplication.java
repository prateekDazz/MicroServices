package com.microservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Accounts Microservice RestAPI Documentation",
				description = "microservice RestAPI Documentation",
				version="v1",
				contact = @Contact(name="Prateek Singh",
				email="prateeksinghengnr@gmail.com",
						url = "www.google.com"
				),
				license = @License(
						name="Apache 2.0",
						url = "www.instagram.com"
				)
		)
)
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

}
