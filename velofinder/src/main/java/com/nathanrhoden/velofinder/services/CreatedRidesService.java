package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatedRidesService {

    private final CreatedRideRepository createdRideRepository;

    @Autowired
    public CreatedRidesService(CreatedRideRepository createdRideRepository) {
        this.createdRideRepository = createdRideRepository;
    }

    public CreatedRide fetchRideById(Long id){
        return createdRideRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Ride not found"));
    }
}
