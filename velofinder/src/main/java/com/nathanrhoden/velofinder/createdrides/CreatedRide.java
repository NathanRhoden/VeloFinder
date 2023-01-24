package com.nathanrhoden.velofinder.createdrides;

import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
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

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

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
}

