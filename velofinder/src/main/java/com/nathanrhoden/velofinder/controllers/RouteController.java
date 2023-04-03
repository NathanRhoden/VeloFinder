package com.nathanrhoden.velofinder.controllers;

import com.nathanrhoden.velofinder.entities.routedata.RouteData;
import com.nathanrhoden.velofinder.services.CreatedRidesService;
import com.nathanrhoden.velofinder.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/route")
public class RouteController {

    private final RouteService routeService;
    private final CreatedRidesService createdRidesService;

    @Autowired
    public RouteController(RouteService routeService, CreatedRidesService createdRidesService) {
        this.routeService = routeService;
        this.createdRidesService = createdRidesService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RouteData> getRouteById(@PathVariable Long id) {
        RouteData route = routeService.fetchRouteData(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PostMapping
    public void uploadGpxFile(@RequestParam(name = "file") MultipartFile multipartFile,
                              @RequestParam(name = "id") Long createdRideId) throws Exception {

        routeService.saveRouteData(createdRideId, multipartFile.getBytes());


    }

    @GetMapping(value = "/ride/{rideId}", produces = MediaType.TEXT_XML_VALUE)
    public Resource fetchRouteGPX(@PathVariable Long rideId) {

        byte[] gpxFileData = routeService.fetchRouteData(rideId)
                .getRouteData();

        return new ByteArrayResource(gpxFileData);


    }
}
