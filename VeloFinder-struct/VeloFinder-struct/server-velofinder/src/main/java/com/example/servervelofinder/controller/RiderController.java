package com.example.servervelofinder.controller;


import com.example.servervelofinder.DTO.RiderDTO;

import com.example.servervelofinder.service.RiderService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rider")
public class RiderController {

    private final RiderService riderService;

    @GetMapping
    public RiderDTO fetchRiderProfile(HttpServletRequest request){
        return riderService.fetchRiderProfileByUsername(request);

    }
    @GetMapping("/get")
    public RiderDTO fetchRiderProfileById(@RequestParam Long id){
        return riderService.fetchRiderById(id);
    }

    @GetMapping("/all")
    public List<RiderDTO> fetchAllRiders(){
        return riderService.fetchAllRidersProfiles();
    }

}
