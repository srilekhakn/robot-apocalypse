package com.survive.robotapocalypse.model;


public enum RobotEnum {

    MODEL("model"),
    SERIALNUMBER("serialNumber"),
    MANUFACTUREDDATE("manufacturedDate"),
    CATEGORY("category");

    private final String robotVariable;

    RobotEnum(final String robotVariable) {
        this.robotVariable = robotVariable;
    }

    public String getRobotVariable() {
        return robotVariable;
    }
}
