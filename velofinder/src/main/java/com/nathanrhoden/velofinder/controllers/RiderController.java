package com.nathanrhoden.velofinder.controllers;

import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.DTO.RiderProfileDTO;
import com.nathanrhoden.velofinder.services.entities.rider.Details;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import com.nathanrhoden.velofinder.services.RiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/rider")
public class RiderController {


    private final RiderService riderService;
    private final Logger log = LoggerFactory.getLogger(RiderController.class);

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
    ResponseEntity<String> createRider(@AuthenticationPrincipal Details details,
                                         @RequestBody RiderDTO riderDTO){

        var d = details.getRider().getId();

        log.info(d.toString());

        riderService.saveRider(riderDTO , d);

        return new ResponseEntity<>("Hit this point" , HttpStatus.OK);
    }

    @GetMapping("/details")
    public Long getLoggedInRiderId(@AuthenticationPrincipal Details details){
        var d = details.getRider().getId();
        return d;
    }


    @GetMapping(path = "/profile")
    ResponseEntity<RiderProfileDTO> fetchLoggedInRider(@AuthenticationPrincipal Details details){

        var loggedRider = riderService.fetchRiderProfile(details.getRider().getId());

        return new ResponseEntity<>(loggedRider , HttpStatus.OK);
    }


}
