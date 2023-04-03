package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RiderProfileDTO {

    private String firstName;

    private String lastName;

    private LocalDate DOB;

    private EXPERIENCE experience;

    private List<CreatedRideDTO> createdRideList;






}
