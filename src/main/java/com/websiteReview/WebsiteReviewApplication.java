package com.websiteReview;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@SecurityScheme(name = "BearerJWT", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", description = "Bearer token for the access")
public class WebsiteReviewApplication {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Website Review System")
                        .version("1.0")
                        .description("API documentation for Website Review System")
                        .termsOfService("Terms of service")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Dipendra")
                                .url("https://localhost:8080")
                                .email("bhattadipen557@gmail.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("License of API")
                                .url("API license URL")));
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteReviewApplication.class, args);
    }
}
