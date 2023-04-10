package com.nathanrhoden.velofinder.security;

import com.nathanrhoden.velofinder.repository.DetailsRepository;
import com.nathanrhoden.velofinder.services.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final DetailsService detailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("api/v1/rider/**").permitAll()
                .anyRequest()
                .hasRole("USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/v1/rider/all");

        return http.build();

    }


    public InMemoryUserDetailsManager detailsManager(){

        UserDetails user1 = User.withUsername("nathan")
                .password(passwordEncoder().encode("pass"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
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
