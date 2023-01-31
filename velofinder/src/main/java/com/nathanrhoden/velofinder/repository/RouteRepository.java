package com.nathanrhoden.velofinder.repository;

import com.nathanrhoden.velofinder.entities.GeoJsonRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<GeoJsonRoute , Long> {

}
