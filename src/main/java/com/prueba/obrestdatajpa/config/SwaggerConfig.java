package com.prueba.obrestdatajpa.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuracion Swwagger para la generacion de documentacion de la API REST
 * 
 * HTML: http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiDetails()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Books API REST", "Library Api Rest Docs", "1.0", "www.google.com",
            new Contact("Fernando", "www.fer.com", "fer_gzhc@outlook.es"), "GNU", "www.gnu.com", Collections.emptyList());
    }
}
