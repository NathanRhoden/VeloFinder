package com.example.servervelofinder.service;

import com.example.servervelofinder.repository.RouteDataRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RouteDataService {


    private final CreatedRideService createdRideService;
    private final RouteDataRepository routeDataRepository;
    private final RiderService riderService;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /*

    public void saveRouteData(byte[] gpxData , HttpServletRequest request){
        var rider = riderService.fetchRiderProfileByUsername(request);

        var createdRide = createdRideService.findCreatedRideById();
    }

     */



}
