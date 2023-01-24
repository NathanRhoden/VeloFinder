package com.nathanrhoden.velofinder.entities.rider;


import com.nathanrhoden.velofinder.createdrides.CreatedRide;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Rider")
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

    @OneToMany(mappedBy = "rider")
    private Set<CreatedRide> createdRides;

    @Enumerated(EnumType.STRING)
    EXPERIENCE riderExperience;

    public Rider(String firstName, String secondName, LocalDate DOB, EXPERIENCE riderExperience) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.DOB = DOB;
        this.riderExperience = riderExperience;
    }
}
