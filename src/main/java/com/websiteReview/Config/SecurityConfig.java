package com.websiteReview.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.websiteReview.Security.JwtAuthenticationEntryPoint;
import com.websiteReview.Security.JwtAuthenticationFilter;
import com.websiteReview.Security.oauth2.CustomOAuth2UserService;
import com.websiteReview.Security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.websiteReview.Security.oauth2.OAuth2AuthenticationFailureHandler;
import com.websiteReview.Security.oauth2.OAuth2AuthenticationSuccessHandler;

// import com.websiteReview.Security.CustomerOAuth2UserService;

@Configuration
@EnableWebMvc
public class SecurityConfig {

        public static final String[] PUBLIC_URLS = {
                        "/user/create",
                        "/auth/**",
                        "/login/oauth2/code/**",
                        "/v3/api-docs",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/",
                        "/error",
                        "/favicon.ico",
                        "/forgot-password/**"
        };

        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private CustomOAuth2UserService customOAuth2UserService;

        @Autowired
        private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

        @Autowired
        private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

        @Autowired
        private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

        @Bean
        public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
                return new HttpCookieOAuth2AuthorizationRequestRepository();
        }

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
                                                .requestMatchers(PUBLIC_URLS)
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET).permitAll()
                                                .anyRequest().authenticated())
                                .oauth2Login(o -> o
                                                .authorizationEndpoint(e -> e
                                                                .baseUri("/oauth2/authorize")
                                                                .authorizationRequestRepository(
                                                                                cookieAuthorizationRequestRepository()))
                                                .redirectionEndpoint(r -> r
                                                                .baseUri("/oauth2/callback/*"))
                                                .userInfoEndpoint(u -> u
                                                                .userService(customOAuth2UserService))
                                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                                .failureHandler(oAuth2AuthenticationFailureHandler))

                                .formLogin(Customizer.withDefaults())
                                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .logout(logout -> logout.permitAll());

                // http.addFilterBefore(new InterceptorFilter(),
                // UsernamePasswordAuthenticationFilter.class);
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
        public FilterRegistrationBean<CorsFilter> corsFilter() {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOrigin("http://localhost:3000"); // Add your frontend origin here
                config.addAllowedMethod("*");
                config.addAllowedHeader("*");
                source.registerCorsConfiguration("/**", config);

                // Create the FilterRegistrationBean for CorsFilter
                FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
                bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Set the desired order
                return bean;
        }

}
