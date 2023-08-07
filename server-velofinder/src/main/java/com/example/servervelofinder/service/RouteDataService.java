package com.example.servervelofinder.service;

import com.example.servervelofinder.entities.CreatedRide;
import com.example.servervelofinder.entities.RouteData;
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
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Long saveRouteData(Long createdRideId, byte[] gpxData, String lat, String lng) {
        CreatedRide createdRide = createdRideService.findCreatedRideById(createdRideId);

        RouteData routeData = new RouteData();
        routeData.setCreatedRide(createdRide);
        routeData.setRouteData(gpxData);
        routeData.setStartingLat(lat);
        routeData.setStartingLng(lng);

        createdRide.setRouteData(routeData);

        return createdRideService.saveCreatedRide(createdRide);
    }

    public RouteData fetchRouteData(Long id) {
        return routeDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }


}
