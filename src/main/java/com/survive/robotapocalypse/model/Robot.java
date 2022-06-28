package com.survive.robotapocalypse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDateTime;

@Entity
@IdClass(RobotCompositeKey.class)
public class Robot {

    @Id
    @Column(name = "model")
    private String model;

    @Id
    @Column(name = "serialNumber")
    private String serialNumber;

    @Column(name = "manufacturedDate")
    private LocalDateTime manufacturedDate;

    @Column(name = "category")
    private RobotCategory category;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(LocalDateTime manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public RobotCategory getCategory() {
        return category;
    }

    public void setCategory(RobotCategory category) {
        this.category = category;
    }
}
