package com.pbank.Cards;

import com.pbank.Cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.SQLOutput;

@SpringBootApplication
@EnableConfigurationProperties(CardsContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "pbank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Pushpak Sune",
						email = "admin@pbank.com",
						url = "https://www.pbank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.pbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "pbank Cards microservice REST API Documentation",
				url = "https://www.pbank.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
		System.out.println("Cards microservices Started");
	}

}
