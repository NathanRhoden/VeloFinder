package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.DTO.CreatedRideDTO;
import com.nathanrhoden.velofinder.services.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatedRidesService {

    private final CreatedRideRepository createdRideRepository;
    private final RouteRepository routeRepository;
    private final RiderRepository riderRepository;

    @Autowired
    public CreatedRidesService(CreatedRideRepository createdRideRepository, RouteRepository routeRepository, RiderRepository riderRepository) {
        this.createdRideRepository = createdRideRepository;
        this.routeRepository = routeRepository;
        this.riderRepository = riderRepository;
    }

    public CreatedRide fetchRideById(Long id){
        return createdRideRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Ride not found"));
    }

    public List<CreatedRideDTO> fetchAllRides(){
        return createdRideRepository.findAll()
                .stream()
                .map(CreatedRideDTO::from)
                .collect(Collectors.toList());

    }


    public Long addCreatedRideToUser(CreatedRideDTO createdRideDTO, Long riderId){
        Rider fetchedRider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("RIDER NOT FOUND"));

        CreatedRide createdRide = new CreatedRide();
        createdRide.setRider(fetchedRider);
        createdRide.setDistance(createdRideDTO.getDistance());
        createdRide.setEventName(createdRideDTO.getEventName());
        createdRide.setExperience(createdRideDTO.getExperience());
        createdRide.setStartDate(createdRideDTO.getStartDate());
        createdRide.setStartTime(createdRideDTO.getStartTime());

         return createdRideRepository.save(createdRide)
                .getId();
    }



}
