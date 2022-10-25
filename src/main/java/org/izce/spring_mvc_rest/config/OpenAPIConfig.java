package org.izce.spring_mvc_rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Izce's Sample Spring MVC REST API")
                .description("Izce's Sample Spring MVC REST API")
                .contact(new Contact().name("Izce").email("admin@izce.com").url("http://izce.com"))
                .version("v0.0.1"));
    }
}