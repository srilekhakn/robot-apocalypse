package com.survive.robotapocalypse.model;

import java.io.Serializable;

public class RobotCompositeKey implements Serializable {
    private String model;
    private String serialNumber;

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
}
