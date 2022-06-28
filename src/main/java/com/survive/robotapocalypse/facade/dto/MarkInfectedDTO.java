package com.survive.robotapocalypse.facade.dto;

import com.survive.robotapocalypse.model.MarkInfected;

public class MarkInfectedDTO {

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

    public static MarkInfected toMarkInfected(final MarkInfectedDTO dto) {
        final MarkInfected markInfected = new MarkInfected();
        markInfected.setInfectedSurvivorId(dto.getInfectedSurvivorId());
        markInfected.setSurvivorId(dto.getSurvivorId());
        return markInfected;
    }

    public static MarkInfectedDTO fromMarkInfected(final MarkInfected markInfected) {
        final MarkInfectedDTO mi = new MarkInfectedDTO();
        mi.setSurvivorId(markInfected.getSurvivorId());
        mi.setInfectedSurvivorId(markInfected.getInfectedSurvivorId());
        return mi;
    }
}
