package com.survive.robotapocalypse.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
public class Survivor {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue
    private Long survivorId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Age")
    private float age;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "resources")
    @OneToMany
    private String resources;

    @Column(name = "infected" ,columnDefinition = "boolean default false")
    private boolean infected;
}
