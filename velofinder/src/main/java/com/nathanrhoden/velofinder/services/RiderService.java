package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.DTO.CreatedRideDTO;
import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.DTO.RiderProfileDTO;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService {


    private final RiderRepository riderRepository;

    @Autowired
    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public List<Rider> fetchAllRiderProfiles() {
        return riderRepository.findAll();
    }

    public Rider fetchRiderById(Long id) {

        return riderRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Rider not found"));
    }


    public RiderProfileDTO fetchRiderProfile(Long riderId) {

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RiderNotFoundException("Rider ID not found"));

        List<CreatedRideDTO> createdRideDTOS = rider.getCreatedRides()
                .stream().map(CreatedRideDTO::from)
                .collect(Collectors.toList());


        return RiderProfileDTO
                .builder()
                .firstName(rider.getFirstName())
                .lastName(rider.getSecondName())
                .DOB(rider.getDOB())
                .experience(rider.getRiderExperience())
                .createdRideList(createdRideDTOS)
                .build();


    }

    public void saveRider(RiderDTO riderDTO , Long riderId) {

        var loggedInRiderProfile = fetchRiderById(riderId);

        loggedInRiderProfile.setFirstName(riderDTO.getFirstName());
        loggedInRiderProfile.setSecondName(riderDTO.getLastName());
        loggedInRiderProfile.setRiderExperience(riderDTO.getExperience());
        loggedInRiderProfile.setDOB(riderDTO.getDOB());


        riderRepository.save(loggedInRiderProfile);

    }




}
