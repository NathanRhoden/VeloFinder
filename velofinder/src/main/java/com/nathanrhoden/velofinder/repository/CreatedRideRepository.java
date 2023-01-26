package com.nathanrhoden.velofinder.repository;

import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedRideRepository extends JpaRepository<CreatedRide , Long> {
}
