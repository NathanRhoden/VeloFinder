package com.example.servervelofinder.repository;

import com.example.servervelofinder.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RiderRepository extends CrudRepository<Rider , Long> {

    Optional<Rider> findByUsername(String username);
}
