package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RiderDTO {

    private String firstName;

    private String lastName;

    private LocalDate DOB;

    private EXPERIENCE experience;

    public RiderDTO(String firstName, String lastName, LocalDate DOB, EXPERIENCE experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.experience = experience;
    }

    public RiderDTO() {
    }

   public static Rider toEntity(RiderDTO riderDTO){
        Rider rider = new Rider();

        rider.setFirstName(riderDTO.getFirstName());
        rider.setSecondName(riderDTO.getLastName());
        rider.setDOB(riderDTO.getDOB());
        rider.setRiderExperience(riderDTO.getExperience());

        return rider;

   }

}
