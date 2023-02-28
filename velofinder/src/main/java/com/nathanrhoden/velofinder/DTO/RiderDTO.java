package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
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

    public RiderDTO(String firstName, String lastName, LocalDate DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
    }

    public RiderDTO() {
    }

   public static Rider toEntity(RiderDTO riderDTO){

   }

}
