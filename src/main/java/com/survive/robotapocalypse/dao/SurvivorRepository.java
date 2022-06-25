package com.survive.robotapocalypse.dao;

import com.survive.robotapocalypse.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorRepository extends JpaRepository<Survivor,Long> {

}
