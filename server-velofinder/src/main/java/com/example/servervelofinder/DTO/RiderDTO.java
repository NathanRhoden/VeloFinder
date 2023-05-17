package com.example.servervelofinder.DTO;

import com.example.servervelofinder.entities.CreatedRide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RiderDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String token;
    private List<CreatedRideDTO> createdRides;
    private String experience;

}
