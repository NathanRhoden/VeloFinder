package com.nathanrhoden.velofinder.entities.createdrides;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "created_rides")
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

    @JsonBackReference
    @ManyToOne(
            fetch = FetchType.EAGER, optional = false
    )
    @JoinColumn(name = "rider_id")
    @JsonIgnore
    private Rider rider;

    @JsonManagedReference
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "routeData_id" , referencedColumnName = "id")
    private RouteData routeData;

    public void setRider(Rider rider) {
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

