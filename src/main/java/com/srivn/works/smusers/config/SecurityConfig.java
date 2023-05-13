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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/sm-users/**").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/swagger-ui/**").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/h2-console/**").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/v3/api-docs/**").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/welcome/**").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/users/**").permitAll();
        //To allow post Request
        http.csrf().disable();
      /*  http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);*/
        return http.build();
    }
}
