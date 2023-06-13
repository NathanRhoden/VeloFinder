package com.nathanrhoden.velofinder.security;

import com.nathanrhoden.velofinder.services.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final String[] ENDPOINTS_WHITELIST = {
            "/signup" ,
            "/h2-console/**",
           // "/api/v1/rider/**",

    };

    private final String LOGIN_PAGE =  "http://localhost:3000/login";
    private final String LOGIN_PAGE_ERROR =  "http://localhost:3000/loginerror";
    private final String LANDING_PAGE = "http://localhost:3000/";

    private final DetailsService detailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .requestMatchers("api/v1/rider/**").hasAuthority("USER")
                        .anyRequest()
                        .authenticated())
                .headers(header -> header.frameOptions().disable())
                .formLogin()
                .defaultSuccessUrl(LANDING_PAGE)
                .failureUrl(LOGIN_PAGE_ERROR);

        return http.build();

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(detailsService);

        return daoAuthenticationProvider;

    }



}
