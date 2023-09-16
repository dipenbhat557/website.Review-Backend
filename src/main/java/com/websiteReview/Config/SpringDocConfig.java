package com.websiteReview.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {

    // Define the name of the authorization header
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Bean to configure the security requirement
    @Bean
    public SecurityRequirement securityRequirement() {
        SecurityRequirement requirement = new SecurityRequirement();

        // Add the authorization header to the security requirement
        requirement.addList(AUTHORIZATION_HEADER);

        return requirement;
    }

    // Bean to configure the security scheme
    @Bean
    public SecurityScheme securityScheme() {
        SecurityScheme scheme = new SecurityScheme();

        // Set the security scheme type to APIKEY
        scheme.setType(SecurityScheme.Type.APIKEY);

        // Specify that the API key is expected in the header
        scheme.setIn(SecurityScheme.In.HEADER);

        // Set the name of the API key header (Authorization in this case)
        scheme.setName(AUTHORIZATION_HEADER);

        return scheme;
    }
}
