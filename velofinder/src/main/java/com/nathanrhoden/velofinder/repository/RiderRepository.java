package com.nathanrhoden.velofinder.repository;

import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider , Long> {

}
