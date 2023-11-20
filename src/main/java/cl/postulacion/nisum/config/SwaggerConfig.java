package cl.postulacion.nisum.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.postulacion.nisum.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Postulacion",
                "Aplicación que expone una API RESTful de creación de usuarios",
                "1.0",
                "Terms of service",
                new Contact("Alex Soto Méndez","https://www.linkedin.com/in/alex-soto-m%C3%A9ndez-a9495376",
                        "alex.soto.29.9.88@gmail.com"),
                "license of API",
                "API license URL",
                Collections.emptyList()
        );
    }
}






