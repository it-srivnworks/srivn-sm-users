package com.srivn.works.smusers.config;

import com.srivn.works.smusers.config.jwt.JwtAuthenticationEntryPoint;
import com.srivn.works.smusers.config.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints
            "/h2-console/**",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher ("/swagger-ui/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher ("/v3/api-docs/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher ("/h2-console/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher ("/welcome/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher ("/users/**")).permitAll()
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**"))
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**"))
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/welcome/**"))
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/users/**"))
                .and()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }
}
