package com.nathanrhoden.velofinder.entities.createdrides;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Created_rides")
public class CreatedRide {

    @Id
    @SequenceGenerator(
            name = "createdRide_sequence",
            sequenceName = "createdRide_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "createdRide_sequence"
    )
    private Long id;

    private LocalDate startDate;
    private LocalTime startTime;

    private int distance;
    private String eventName;

    @Enumerated(EnumType.STRING)
    private EXPERIENCE matchedRiderExperience;

    @ManyToOne(
            fetch = FetchType.LAZY, optional = false
    )
    @JoinColumn(name = "rider_id")
    @JsonIgnore
    private Rider rider;

    @OneToOne(cascade = CascadeType.ALL)
    private RouteData routeData;

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public CreatedRide(LocalDate startDate, LocalTime startTime, int distance, String eventName, EXPERIENCE matchedRiderExperience, Rider rider) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.distance = distance;
        this.eventName = eventName;
        this.matchedRiderExperience = matchedRiderExperience;
        this.rider = rider;
    }

    public CreatedRide(LocalDate startDate, LocalTime startTime, int distance, String eventName, EXPERIENCE matchedRiderExperience, Rider rider, RouteData routeData) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.distance = distance;
        this.eventName = eventName;
        this.matchedRiderExperience = matchedRiderExperience;
        this.rider = rider;
        this.routeData = routeData;
    }
}

