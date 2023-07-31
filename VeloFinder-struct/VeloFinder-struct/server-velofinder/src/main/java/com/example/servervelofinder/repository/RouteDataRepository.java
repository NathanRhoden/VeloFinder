package com.example.servervelofinder.repository;

import com.example.servervelofinder.entities.RouteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RouteDataRepository  extends CrudRepository<RouteData , Long> {
}
