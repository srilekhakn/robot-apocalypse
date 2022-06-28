package com.survive.robotapocalypse.dao;

import com.survive.robotapocalypse.model.MarkInfected;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkInfectedRepository extends JpaRepository<MarkInfected,String> {
    boolean existsBySurvivorIdAndInfectedSurvivorId(String survivorId, String infectedSurvivorId);
    int countByInfectedSurvivorId(String infectedSurvivorId);
}