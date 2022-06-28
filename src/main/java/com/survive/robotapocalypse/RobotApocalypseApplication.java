package com.survive.robotapocalypse;

import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.model.Resources;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "Robot Apocalypse Coding Task", version = "0.0.1", description = "Robot apocalypse Coding Task API Documentation.")
)
@SpringBootApplication
public class RobotApocalypseApplication implements CommandLineRunner {

    @Autowired
    ResourcesRepository resourcesRepository;

    public static void main(String[] args) {
        SpringApplication.run(RobotApocalypseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Inserting the data in the Resources table.
        Resources resources = new Resources(1L, "Water");
        Resources resources1 = new Resources(2L, "Food");
        Resources resources2 = new Resources(3L, "Medication");
        Resources resources3 = new Resources(4L, "Ammunition");

        // save method
        resourcesRepository.save(resources);
        resourcesRepository.save(resources1);
        resourcesRepository.save(resources2);
        resourcesRepository.save(resources3);
    }

}
