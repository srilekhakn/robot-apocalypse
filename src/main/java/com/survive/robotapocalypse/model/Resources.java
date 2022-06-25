package com.survive.robotapocalypse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resources {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String Name;
}
