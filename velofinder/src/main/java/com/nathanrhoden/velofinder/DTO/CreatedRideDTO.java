package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class CreatedRideDTO {

    private Long id;

    private LocalDate startDate;

    private LocalTime startTime;

    private int distance;

    private String eventName;

    private EXPERIENCE matchedRiderExperience;

    private RouteData routeData;

    public CreatedRideDTO(Long id, LocalDate startDate, LocalTime startTime,
                          int distance, String eventName,
                          EXPERIENCE matchedRiderExperience, RouteData routeData) {
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.distance = distance;
        this.eventName = eventName;
        this.matchedRiderExperience = matchedRiderExperience;
        this.routeData = routeData;
    }

    public static CreatedRideDTO from(CreatedRide createdRide){

        return new CreatedRideDTOBuilder()
               .id(createdRide.getId())
               .eventName(createdRide.getEventName())
               .distance(createdRide.getDistance())
               .matchedRiderExperience(createdRide.getMatchedRiderExperience())
               .startDate(createdRide.getStartDate())
               .startTime(createdRide.getStartTime())
               .routeData(createdRide.getRouteData())
               .build();

    }



}
