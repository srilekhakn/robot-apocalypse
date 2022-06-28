package com.survive.robotapocalypse.facade.dto;

import com.survive.robotapocalypse.model.Robot;
import com.survive.robotapocalypse.model.RobotCategory;

import java.time.LocalDateTime;

public class RobotDTO {

    private String model;

    private String serialNumber;

    private LocalDateTime manufacturedDate;

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

    public static Robot toRobot(RobotDTO dto) {
        Robot robot = new Robot();
        robot.setModel(dto.getModel());
        robot.setCategory(dto.getCategory());
        robot.setManufacturedDate(dto.getManufacturedDate());
        robot.setSerialNumber(dto.getSerialNumber());
        return robot;
    }

    public static RobotDTO fromRobot(Robot robot) {
        RobotDTO robotDTO = new RobotDTO();
        robotDTO.setModel(robot.getModel());
        robotDTO.setCategory(robot.getCategory());
        robotDTO.setManufacturedDate(robot.getManufacturedDate());
        robotDTO.setSerialNumber(robot.getSerialNumber());
        return robotDTO;
    }
}