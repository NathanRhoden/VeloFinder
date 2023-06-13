package com.nathanrhoden.velofinder.repository;

import com.nathanrhoden.velofinder.services.entities.rider.routedata.RouteData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteData, Long> {

}
