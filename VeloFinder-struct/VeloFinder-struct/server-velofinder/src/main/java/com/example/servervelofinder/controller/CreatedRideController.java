package com.example.servervelofinder.controller;

import com.example.servervelofinder.DTO.CreatedRideDTO;
import com.example.servervelofinder.DTO.StartingCoordinatesDTO;
import com.example.servervelofinder.entities.CreatedRide;
import com.example.servervelofinder.service.CreatedRideService;
import com.example.servervelofinder.service.RouteDataService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/create-ride")
public class CreatedRideController {

    private final CreatedRideService createdRideService;
    private final RouteDataService routeDataService;


    @PostMapping()
    public CreatedRideDTO addRide(HttpServletRequest request ,
                                  @RequestBody CreatedRideDTO createdRideDTO){
        return createdRideService.addCreatedRideToUserProfile(request ,createdRideDTO);
    }

    @PostMapping("/user")
    public List<CreatedRideDTO> fetchAuthenticatedUserRides(HttpServletRequest request){
        return createdRideService.fetchAuthenticatedUserRideData(request);
    }

    @GetMapping("/ride/{rideId}")
    public CreatedRide fetchCreatedRideById(@PathVariable Long rideId){
        return createdRideService.findCreatedRideById(rideId);
    }


    @GetMapping("/all")
    public List<CreatedRideDTO> fetchAllCreatedRides(){
        return createdRideService.fetchAllCreatedRides();
    }

    @PostMapping("/gpx")
    public void uploadGpxFile(@RequestParam(name = "file") MultipartFile multipartFile,
                              @RequestParam(name = "id") Long createdRideId ,
                              @RequestParam(name = "lat")  String lat,
                              @RequestParam(name = "lng") String lng
    ) throws Exception {

        routeDataService.saveRouteData(createdRideId, multipartFile.getBytes() , lat , lng);


    }

    @GetMapping(value = "/gpx/{rideId}", produces = MediaType.TEXT_XML_VALUE)
    public Resource fetchRouteGPX(@PathVariable Long rideId) {

        byte[] gpxFileData = routeDataService.fetchRouteData(rideId)
                .getRouteData();

        return new ByteArrayResource(gpxFileData);
    }

    @GetMapping("/cluster")
    public List<StartingCoordinatesDTO> fetchClusterData(){
        return createdRideService.fetchClusterData();
    }

}
