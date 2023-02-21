package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatedRidesService {

    private final CreatedRideRepository createdRideRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public CreatedRidesService(CreatedRideRepository createdRideRepository, RouteRepository routeRepository) {
        this.createdRideRepository = createdRideRepository;
        this.routeRepository = routeRepository;
    }

    public CreatedRide fetchRideById(Long id){
        return createdRideRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Ride not found"));
    }




}
