package com.example.servervelofinder.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.servervelofinder.DTO.CreatedRideDTO;
import com.example.servervelofinder.entities.CreatedRide;
import com.example.servervelofinder.exceptions.AppException;
import com.example.servervelofinder.mapper.CreatedRideMapper;
import com.example.servervelofinder.repository.CreatedRideRepository;
import com.example.servervelofinder.repository.RiderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatedRideService {

    private final CreatedRideMapper createdRideMapper;
    private final CreatedRideRepository createdRideRepository;
    private final RiderRepository riderRepository;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public CreatedRideDTO addCreatedRideToUserProfile(HttpServletRequest request , CreatedRideDTO createdRideDTO
                                                       ){

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        String[] elements = token.split(" ");

        String username = "";

        if(elements.length == 2 && "Bearer".equals(elements[0])){

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decoded = verifier.verify(elements[1]);

            username = decoded.getIssuer();

        }

        var rider = riderRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("USER NOT FOUND"));



        CreatedRide createdRide = CreatedRide.builder()
                .eventName(createdRideDTO.getEventName())
                .startDate(LocalDate.now())
                .startTime(LocalTime.now())
                .experience(createdRideDTO.getExperience())
                .distance(createdRideDTO.getDistance())
                .rider(rider)
                .build();


        rider.getCreatedRides().add(createdRide);

        System.out.println(createdRideMapper.toCreatedRideDTO(createdRide));

        createdRideRepository.save(createdRide);

        return createdRideMapper.toCreatedRideDTO(createdRide);

    }


    public List<CreatedRideDTO> fetchAllCreatedRides(){
        List<CreatedRideDTO> rideDTOList = new ArrayList<>();

        var iterator = createdRideRepository.findAll();

        iterator.forEach(
                (createdRide -> {
                    rideDTOList.add(createdRideMapper.toCreatedRideDTO(createdRide));
                })
        );

        return rideDTOList;

    }

    public CreatedRide findCreatedRideById(Long id){
        return createdRideRepository.findById(id)
                .orElseThrow(() -> new AppException("Ride nto found" , HttpStatus.BAD_REQUEST));

    }

    public Long saveCreatedRide(CreatedRide ride){
        return createdRideRepository.save(ride)
                .getId();
    }


}
