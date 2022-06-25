package com.survive.robotapocalypse.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "serialNumber")
    private String serialNumber;

    @Column(name = "manufacturedDate")
    private LocalDateTime manufacturedDate;

    @Column(name = "category")
    private String category;

}
