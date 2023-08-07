package com.example.servervelofinder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "routeData")
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

    private String startingLat;
    private String startingLng;

    @Lob
    @Column(name = "route", columnDefinition = "MEDIUMBLOB")
    private byte[] routeData;

    @JsonBackReference
    @OneToOne(mappedBy = "routeData", cascade = CascadeType.ALL)
    private CreatedRide createdRide;

    public RouteData(byte[] routeData) {
        this.routeData = routeData;
    }

    public RouteData() {
    }


}
