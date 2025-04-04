package com.bci.user_service.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.bci.user_service.components.utils.constants.APIField.ARTEFACT_ID;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("User Auth API")
                        .description("API para registro de usuarios  y generacion de token.")
                        .version("v1.0")
                        .contact(new Contact().name("Soporte Técnico").email("stefhani@hotmail.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://example.com/docs"));
    }

    @Bean
    public GroupedOpenApi registrationApi() {
        return GroupedOpenApi.builder()
                .group(ARTEFACT_ID)
                .packagesToScan("com.bci.user_service.controllers","com.bci.user_service.dto")
                .build();
    }
}
