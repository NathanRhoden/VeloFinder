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
    private String route;

    @JsonBackReference
    @OneToOne(mappedBy = "routeData")
    private CreatedRide createdRide;

    public RouteData(String route) {
        this.route = route;
    }

    public RouteData() {
    }

    public Long getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }
}
