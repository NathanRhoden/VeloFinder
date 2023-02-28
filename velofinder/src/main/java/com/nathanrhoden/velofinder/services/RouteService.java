package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Clob;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final CreatedRideRepository createdRideRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, CreatedRideRepository createdRideRepository) {
        this.routeRepository = routeRepository;
        this.createdRideRepository = createdRideRepository;
    }

    public RouteData fetchRouteData(Long id){
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    public Long saveRouteData(Long createdRideId , byte[] gpxData){
        CreatedRide ride = createdRideRepository.findById(createdRideId)
                .orElseThrow(() -> new RuntimeException("ID NOT FOUND"));

        RouteData routeData = new RouteData();
        routeData.setCreatedRide(ride);
        routeData.setRouteData(gpxData);

        return routeRepository.save(routeData)
                .getId();
    }

}
