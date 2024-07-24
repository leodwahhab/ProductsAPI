package com.example.cursoSpring.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //indica para o Spring que esta é uma classe de configuração
@EnableWebSecurity //habilita a configuração do web security nesta classe
//abrange toda a infra relacionada a segurança da aplicação
public class SecurityConfiguration {

    @Bean
    //determina a corrente de filtros que serão aplicados
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.
                        requestMatchers(HttpMethod.POST, "/products")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .build();
    }
}
