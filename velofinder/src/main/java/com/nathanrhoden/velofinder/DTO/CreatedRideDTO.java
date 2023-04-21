package com.nathanrhoden.velofinder.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nathanrhoden.velofinder.entities.routedata.RouteData;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.services.RouteService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    private EXPERIENCE experience;

    private String startingLocation;

    @JsonIgnore
    private RouteData routeData;


    public CreatedRideDTO(Long id, LocalDate startDate, LocalTime startTime, int distance,
                          String eventName, EXPERIENCE experience,
                          String startingLocation, RouteData routeData) {
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.distance = distance;
        this.eventName = eventName;
        this.experience = experience;
        this.startingLocation = startingLocation;
        this.routeData = routeData;
    }

    @Autowired


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
