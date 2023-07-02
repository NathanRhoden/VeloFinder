package com.example.servervelofinder.security;

import com.example.servervelofinder.config.JwtAuthFilter;
import com.example.servervelofinder.config.RiderAuthenticationEntryPoint;
import com.example.servervelofinder.config.RiderAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final RiderAuthenticationProvider authenticationProvider;
    private final RiderAuthenticationEntryPoint riderAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(riderAuthenticationEntryPoint)
                        .and()
                        .addFilterBefore(new JwtAuthFilter(authenticationProvider) , BasicAuthenticationFilter.class)
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/register" ,"/login","/create-ride/all"
                                , "/create-ride/cluster")
                        .permitAll().anyRequest().authenticated()
                ).headers(header -> header.frameOptions().disable());

        return http.build();
    }



}
