package com.nathanrhoden.velofinder.entities;

import jakarta.persistence.*;

import java.sql.Clob;

@Entity
@Table(name= "routes")
public class RouteData {

    @Id
    @SequenceGenerator(
            name = "routes_sequence",
            sequenceName = "routes_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "routes_sequence"
    )
    private Long id;

    @Lob
    @Column(name = "route", columnDefinition="CLOB")
    private Clob route;

    public RouteData(Clob route) {
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public Clob getRoute() {
        return route;
    }
}
