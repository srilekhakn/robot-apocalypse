package com.survive.robotapocalypse.dao;

import com.survive.robotapocalypse.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
