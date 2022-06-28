package com.survive.robotapocalypse.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.dao.RobotRepository;
import com.survive.robotapocalypse.facade.dto.RobotDTO;
import com.survive.robotapocalypse.model.Robot;
import com.survive.robotapocalypse.model.RobotCategory;
import com.survive.robotapocalypse.service.RobotService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RobotController.class)
public class RobotControllerTest {

    @Autowired
    ObjectMapper mapper;
    @MockBean
    RobotService robotService;

    @MockBean
    RobotRepository robotRepository;

    @MockBean
    ResourcesRepository resourcesRepository;

    @Autowired
    MockMvc mockMvc;

    static RobotDTO robotDTO;

    @BeforeAll
    public static void init() {
        //      Initialize RobotDTO object
        robotDTO = new RobotDTO();
        robotDTO.setModel("MO1");
        robotDTO.setSerialNumber("SER01");
        robotDTO.setCategory(RobotCategory.FLYING);
        robotDTO.setManufacturedDate(LocalDateTime.now());
    }

    @Test
    void TestRobotInsert() throws Exception {
        //    Test Robot Insert
        Robot robot = new Robot();
        robot.setCategory(RobotCategory.FLYING);
        robot.setModel("MO1");
        robot.setSerialNumber("SER01");
        robot.setManufacturedDate(LocalDateTime.now());

        Mockito.when(robotService.insert(any())).thenReturn(robot);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/robot")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(robotDTO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serialNumber", notNullValue()));
    }

    @Test
    void TestRobotFetch() throws Exception {
        //    Test Robot Fetch

        Robot robot = new Robot();
        robot.setCategory(RobotCategory.FLYING);
        robot.setModel("MO1");
        robot.setSerialNumber("SER01");
        robot.setManufacturedDate(LocalDateTime.now());

        Robot robot1 = new Robot();
        robot1.setCategory(RobotCategory.FLYING);
        robot1.setModel("MO1");
        robot1.setSerialNumber("SER01");
        robot1.setManufacturedDate(LocalDateTime.now());

        List<Robot> robotList = new ArrayList<>();
        robotList.add(robot);
        robotList.add(robot1);

        Mockito.when(robotService.fetch(any())).thenReturn(robotList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/robot/?sortBy=MODEL")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}
