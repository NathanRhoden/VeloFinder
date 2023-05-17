package com.example.servervelofinder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
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


    private String experience;



    @JsonBackReference
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
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



    public Resource convertDataToGPX(byte[] routeData) {

        return new ByteArrayResource(routeData);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreatedRide that = (CreatedRide) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

