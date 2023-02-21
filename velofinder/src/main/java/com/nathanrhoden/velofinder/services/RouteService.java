package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Clob;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RouteData fetchRouteData(Long id){
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }


}
