package com.nathanrhoden.velofinder.controllers;

import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.DTO.RiderProfileDTO;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rider")
public class RiderController {


    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping(path = "/all")
    ResponseEntity<List<Rider>> fetchAllRiderProfiles(){
        return new ResponseEntity<>(riderService.fetchAllRiderProfiles() , HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<RiderProfileDTO> fetchRiderProfileByID(@PathVariable Long id){

        RiderProfileDTO foundRider = riderService.fetchRiderProfile(id);

        return new ResponseEntity<>(foundRider , HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    ResponseEntity<RiderDTO> createRider(@RequestBody RiderDTO riderDTO){
        riderService.saveRider(riderDTO);

        return new ResponseEntity<>(riderDTO , HttpStatus.OK);
    }

}
