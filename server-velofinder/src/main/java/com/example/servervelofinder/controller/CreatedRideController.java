package com.example.servervelofinder.controller;

import com.example.servervelofinder.DTO.CreatedRideDTO;
import com.example.servervelofinder.entities.CreatedRide;
import com.example.servervelofinder.service.CreatedRideService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/create-ride")
public class CreatedRideController {

    private final CreatedRideService createdRideService;


    @PostMapping()
    public CreatedRideDTO addRide(HttpServletRequest request , @RequestBody CreatedRideDTO createdRideDTO){
        return createdRideService.addCreatedRideToUserProfile(request ,createdRideDTO);
    }


    @GetMapping("/all")
    public List<CreatedRideDTO> fetchAllCreatedRides(){
        return createdRideService.fetchAllCreatedRides();
    }


}
