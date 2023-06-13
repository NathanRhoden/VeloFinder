package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.services.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RiderDTO {

    private String firstName;

    private String lastName;

    private LocalDate DOB;

    private EXPERIENCE experience;

   public static Rider toEntity(RiderDTO riderDTO){
        Rider rider = new Rider();

        rider.setFirstName(riderDTO.getFirstName());
        rider.setSecondName(riderDTO.getLastName());
        rider.setDOB(riderDTO.getDOB());
        rider.setRiderExperience(riderDTO.getExperience());

        return rider;

   }

}
