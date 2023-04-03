package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.DTO.CreatedRideDTO;
import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.DTO.RiderProfileDTO;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService {


   private final RiderRepository riderRepository;

   @Autowired
   public RiderService(RiderRepository riderRepository) {
      this.riderRepository = riderRepository;
   }

   public List<Rider> fetchAllRiderProfiles(){
       return riderRepository.findAll();
   }


    public RiderProfileDTO fetchRiderProfile(Long riderId){

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow( () -> new RiderNotFoundException("Rider ID not found"));

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

   public Long saveRider(RiderDTO riderDTO){
       return riderRepository.save(RiderDTO.toEntity(riderDTO))
               .getId();
   }




}
