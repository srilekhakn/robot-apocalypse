package com.survive.robotapocalypse.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survive.robotapocalypse.dao.SurvivorRepository;
import com.survive.robotapocalypse.facade.dto.SurvivorDTO;
import com.survive.robotapocalypse.model.GenderEnum;
import com.survive.robotapocalypse.service.SurvivorService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SurvivorController.class)
public class SurvivorControllerTest {

    static SurvivorDTO survivorDTO;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    SurvivorService survivorService;
    @MockBean
    SurvivorRepository survivorRepository;

    @BeforeAll
    public static void init() {

        survivorDTO = new SurvivorDTO();
        survivorDTO.setSurvivorId("1");
        survivorDTO.setAge(25);
        survivorDTO.setGender(GenderEnum.FEMALE);
        survivorDTO.setLatitude(20.89);
        survivorDTO.setLongitude(2.34);
        survivorDTO.setName("Srilekha");

    }

}
