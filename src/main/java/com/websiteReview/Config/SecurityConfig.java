package com.websiteReview.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.websiteReview.Security.CustomerOAuth2UserService;
import com.websiteReview.Security.JwtAuthenticationEntryPoint;
import com.websiteReview.Security.JwtAuthenticationFilter;

import jakarta.servlet.Filter;

// import com.websiteReview.Security.CustomerOAuth2UserService;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    // @Autowired
    // private CustomerOAuth2UserService customerOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/user/create","/auth/**", "/login/oauth2/code/**")
                        .permitAll())
                // .oauth2Login(o -> o
                //         .defaultSuccessUrl("/auth/oauth2LoginSuccess")
                //         .userInfoEndpoint(u -> u
                //                 .oidcUserService(this.oidcUserService())
                //                 .userService(customerOAuth2UserService))
                //                 .defaultSuccessUrl("/auth/oauth2LoginSuccess", true))
                // .formLogin(Customizer.withDefaults())
                .exceptionHandling(ex ->
                ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .logout(logout -> logout.permitAll());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    // registering CORS filter
    @Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("Context-Type");
        configuration.addAllowedHeader("Accept");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("OPTIONS");
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(new CorsFilter(source));
        bean.addUrlPatterns("/secured/**");
        bean.setOrder(-110);

        return bean;
    }

    // private OidcUserService oidcUserService() {
    //     OidcUserService oidcUserService = new OidcUserService();
    //     return oidcUserService;
    // }

}
