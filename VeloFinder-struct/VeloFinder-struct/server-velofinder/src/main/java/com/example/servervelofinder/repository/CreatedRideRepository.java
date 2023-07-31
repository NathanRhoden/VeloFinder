package com.example.servervelofinder.repository;

import com.example.servervelofinder.entities.CreatedRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CreatedRideRepository extends CrudRepository<CreatedRide , Long> {
}
