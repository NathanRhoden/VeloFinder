package com.example.servervelofinder.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.servervelofinder.DTO.CredentialsDTO;
import com.example.servervelofinder.DTO.RiderDTO;
import com.example.servervelofinder.DTO.SignUpDto;
import com.example.servervelofinder.config.RiderAuthenticationProvider;
import com.example.servervelofinder.entities.Rider;
import com.example.servervelofinder.exceptions.AppException;
import com.example.servervelofinder.mapper.RiderMapper;
import com.example.servervelofinder.repository.RiderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;
    private final RiderMapper riderMapper;
    private final PasswordEncoder passwordEncoder;


    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public RiderDTO findByUsername(String username) {
        Rider rider = riderRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Username not found", HttpStatus.NOT_FOUND));

        return riderMapper.toRiderDTONoToken(rider);

    }

    public RiderDTO login(CredentialsDTO credentialsDTO) {
        Rider rider = riderRepository.findByUsername(credentialsDTO.getUsername())
                .orElseThrow(() -> new AppException("Username not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), rider.getPassword())) {
            return riderMapper.toRiderDTO(rider);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);

    }

    public RiderDTO register(SignUpDto signUpDto) {

        Optional<Rider> optionalRider = riderRepository.findByUsername(signUpDto.getUsername());

        if (optionalRider.isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Rider newRider = riderMapper.signUpDTOToRider(signUpDto);

        newRider.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        newRider.setCreatedRides(Collections.emptyList());

        Rider savedRider = riderRepository.save(newRider);

        return riderMapper.toRiderDTO(savedRider);

    }

    public RiderDTO fetchRiderById(Long id) {
        var rider = riderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO USER FOUND"));
        return riderMapper.toRiderDTONoToken(rider);
    }


    public List<RiderDTO> fetchAllRidersProfiles() {
        var iterator = riderRepository.findAll();

        List<RiderDTO> riderList = new ArrayList<>();

        iterator.forEach((rider) -> {
            riderList.add(riderMapper.toRiderDTO(rider));
        });

        return riderList;
    }


    public RiderDTO fetchRiderProfileByUsername(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println(token);

        String[] elements = token.split(" ");

        String username = "";

        if (elements.length == 2 && "Bearer".equals(elements[0])) {


            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decoded = verifier.verify(elements[1]);

            username = decoded.getIssuer();

        }

        return findByUsername(username);


    }


}
