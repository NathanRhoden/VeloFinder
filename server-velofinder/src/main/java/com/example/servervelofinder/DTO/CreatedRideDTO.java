package com.example.servervelofinder.DTO;

import com.example.servervelofinder.entities.Rider;
import com.example.servervelofinder.entities.RouteData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreatedRideDTO {

    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private int distance;
    private String eventName;
    private String experience;
    private RouteData routeData;

    @JsonIgnore
    private Rider rider;



}
