package com.pbank;

import com.pbank.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(AccountsContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Pbank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Pushpak Sune",
						email = "pbank@gmail.com",
						url = "https://www.pbank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.pbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "PBank Accounts microservice REST API Documentation",
				url = "https://www.pbank.com/swagger-ui.html"
		)
)
public class FirstBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstBankApplication.class, args);
		System.out.println("First Microservices is up and running");
	}

}
