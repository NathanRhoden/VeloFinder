package com.nathanrhoden.velofinder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name= "routeData")
@Getter
@Setter
public class RouteData {

    @Id
    @SequenceGenerator(
            name = "route_sequence",
            sequenceName = "route_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "route_sequence"
    )
    private Long id;

    @Lob
    @Column(name = "route", columnDefinition="CLOB")
    private byte[] routeData;

    @JsonBackReference
    @OneToOne(mappedBy = "routeData" , cascade = CascadeType.ALL)
    private CreatedRide createdRide;

    public RouteData(byte[] routeData) {
        this.routeData = routeData;
    }
    public RouteData() {
    }


}
