package com.example.fruitapp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(
                List.of("http://localhost:5173"));

        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE"));

        config.setAllowedHeaders(
                List.of("*"));

        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source
                = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Authentication API
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // USER MANAGEMENT
                        // ADMIN ONLY
                        .requestMatchers(
                                "/api/users/**"
                        ).hasRole("ADMIN")

                        // VIEW FRUITS
                        // USER + ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/fruits/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // CREATE FRUIT
                        // ADMIN ONLY
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/fruits/**"
                        ).hasRole("ADMIN")

                        // UPDATE FRUIT
                        // ADMIN ONLY
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/fruits/**"
                        ).hasRole("ADMIN")

                        // DELETE FRUIT
                        // ADMIN ONLY
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/fruits/**"
                        ).hasRole("ADMIN")

                        // ALL OTHER REQUESTS
                        .anyRequest()
                        .authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

