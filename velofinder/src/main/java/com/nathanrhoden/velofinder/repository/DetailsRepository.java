package com.nathanrhoden.velofinder.repository;

import com.nathanrhoden.velofinder.entities.rider.Details;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details , Long> {

    Details findByUsername(String username);
}
