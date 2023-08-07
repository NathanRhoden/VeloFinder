package com.example.servervelofinder.controller;

import com.example.servervelofinder.DTO.CredentialsDTO;
import com.example.servervelofinder.DTO.RiderDTO;
import com.example.servervelofinder.DTO.SignUpDto;
import com.example.servervelofinder.config.RiderAuthenticationProvider;
import com.example.servervelofinder.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final RiderService riderService;
    private final RiderAuthenticationProvider riderAuthenticationProvider;


    @PostMapping("/login")
    public ResponseEntity<RiderDTO> login(@RequestBody CredentialsDTO credentialsDTO) {
        RiderDTO riderDTO = riderService.login(credentialsDTO);
        riderDTO.setToken(riderAuthenticationProvider.createToken(credentialsDTO.getUsername()));

        return ResponseEntity.ok(riderDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<RiderDTO> register(@RequestBody SignUpDto user) {

        RiderDTO createdUser = riderService.register(user);
        createdUser.setToken(riderAuthenticationProvider.createToken(user.getUsername()));
        return ResponseEntity.created(URI.create("/users" + createdUser.getId())).body(createdUser);
    }
}
