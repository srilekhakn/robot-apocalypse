package com.survive.robotapocalypse.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MarkInfected {

    @Id
    private String survivorId;

    private String infectedSurvivorId;

    public String getSurvivorId() {
        return survivorId;
    }

    public void setSurvivorId(String survivorId) {
        this.survivorId = survivorId;
    }

    public String getInfectedSurvivorId() {
        return infectedSurvivorId;
    }

    public void setInfectedSurvivorId(String infectedSurvivorId) {
        this.infectedSurvivorId = infectedSurvivorId;
    }
}
