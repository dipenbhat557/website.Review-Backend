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
// Swagger SecurityScheme annotation to specify Bearer token security
@SecurityScheme(name = "BearerJWT", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", description = "Bearer token for access")
public class WebsiteReviewApplication {

    // Bean definition for ModelMapper, used for object mapping
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    // Bean definition for custom OpenAPI documentation
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Website Review System") // Title of the API
                        .version("1.0") // API version
                        .description("API documentation for Website Review System") // Description of the API
                        .termsOfService("Terms of service") // Terms of service URL or information
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Dipendra") // Contact person's name
                                .url("https://localhost:8080") // Contact person's URL
                                .email("bhattadipen557@gmail.com")) // Contact person's email
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("License of API") // Name of the API license
                                .url("API license URL"))); // URL to the API license
    }

    public static void main(String[] args) {
        // Entry point of the Spring Boot application
        SpringApplication.run(WebsiteReviewApplication.class, args);
    }
}
