package com.nathanrhoden.velofinder.controllers;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RouteData> getRouteById(@PathVariable Long id){
        RouteData route = routeService.fetchRouteData(id);
        return new ResponseEntity<>(route , HttpStatus.OK);
    }
}
