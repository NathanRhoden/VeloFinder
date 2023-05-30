package com.nathanrhoden.velofinder.services.entities.rider;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nathanrhoden.velofinder.services.entities.createdrides.CreatedRide;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "riders")
public class Rider {

    @Id
    @SequenceGenerator(
            name = "rider_sequence",
            sequenceName = "rider_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rider_sequence"
    )
    Long id;

    String firstName;
    String secondName;
    LocalDate DOB;

    @JsonManagedReference
    @OneToMany(mappedBy = "rider" ,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CreatedRide> createdRides;

    @Enumerated(EnumType.STRING)
    EXPERIENCE riderExperience;

    public Rider(String firstName, String secondName, LocalDate DOB, EXPERIENCE riderExperience) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.DOB = DOB;
        this.riderExperience = riderExperience;
    }
}
