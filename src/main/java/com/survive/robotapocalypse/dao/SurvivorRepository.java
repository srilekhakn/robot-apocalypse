package com.survive.robotapocalypse.dao;

import com.survive.robotapocalypse.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurvivorRepository extends JpaRepository<Survivor, String> {

    boolean existsBySurvivorId(String survivorId);

    List<Survivor> findByInfected(boolean isInfected);

    long countByInfected(boolean isInfected);

    Survivor findBySurvivorId(String infectedSurvivorId);
}