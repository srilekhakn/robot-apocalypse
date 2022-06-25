package com.survive.robotapocalypse.dao;

import com.survive.robotapocalypse.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotRepository extends JpaRepository<Robot,Long> {
}
