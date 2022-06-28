package com.survive.robotapocalypse.facade;

import com.survive.robotapocalypse.dao.MarkInfectedRepository;
import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.dao.RobotRepository;
import com.survive.robotapocalypse.dao.SurvivorRepository;
import com.survive.robotapocalypse.facade.dto.SurvivorDTO;
import com.survive.robotapocalypse.model.GenderEnum;
import com.survive.robotapocalypse.model.ResourcesEnum;
import com.survive.robotapocalypse.service.RobotService;
import com.survive.robotapocalypse.service.SurvivorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(RobotController.class)
public class RobotControllerTest {

    @MockBean
    RobotService robotService;

    @MockBean
    RobotRepository robotRepository;

    @BeforeAll
    public static void init() {
        //      Initialize RobotDTO object
    }

    @Test
    public void TestRobotInsert() throws Exception {
        //    Test Robot Insert
    }

    @Test
    public void TestRobotFetch() throws Exception {
        //    Test Robot Fetch
    }
}
