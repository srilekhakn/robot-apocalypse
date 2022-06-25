package com.survive.robotapocalypse.model;

public enum RobotCategory {

    LAND("Land"),
    FLYING("Flying");

    private final String category;

    RobotCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
