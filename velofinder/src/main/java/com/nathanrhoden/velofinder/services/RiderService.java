package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.exceptions.RiderNotFoundException;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Rider fetchRiderProfile(Long riderId){
        return riderRepository.findById(riderId)
                .orElseThrow( () -> new RiderNotFoundException("Rider ID not found"));
   }

   public Long saveRider(RiderDTO riderDTO){
       return riderRepository.save(RiderDTO.toEntity(riderDTO))
               .getId();
   }




}
