package com.nathanrhoden.velofinder.controllers;

import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.services.CreatedRidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/createdride")
public class CreatedRideController {

    private final CreatedRidesService createdRidesService;

    @Autowired
    public CreatedRideController(CreatedRidesService createdRidesService) {
        this.createdRidesService = createdRidesService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreatedRide> fetchById(@PathVariable Long id){
        CreatedRide ride = createdRidesService.fetchRideById(id);

        return new ResponseEntity<>(ride, HttpStatus.OK);
    }
}
