package com.nathanrhoden.velofinder.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nathanrhoden.velofinder.services.entities.rider.routedata.RouteData;
import com.nathanrhoden.velofinder.services.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.services.entities.rider.EXPERIENCE;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedRideDTO {

    private Long id;

    private LocalDate startDate;

    private LocalTime startTime;

    private int distance;

    private String eventName;

    private EXPERIENCE experience;

    private String startingLocation;

    @JsonIgnore
    private RouteData routeData;

    public static CreatedRideDTO from(CreatedRide createdRide ){

        return new CreatedRideDTOBuilder()
               .eventName(createdRide.getEventName())
               .distance(createdRide.getDistance())
               .experience(createdRide.getExperience())
               .startDate(createdRide.getStartDate())
               .startTime(createdRide.getStartTime())
               .routeData(createdRide.getRouteData())
                .id(createdRide.getId())
               .build();

    }



}
