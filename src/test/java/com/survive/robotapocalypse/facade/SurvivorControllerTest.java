package com.survive.robotapocalypse.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survive.robotapocalypse.dao.MarkInfectedRepository;
import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.dao.SurvivorRepository;
import com.survive.robotapocalypse.facade.dto.SurvivorDTO;
import com.survive.robotapocalypse.model.GenderEnum;
import com.survive.robotapocalypse.model.Resources;
import com.survive.robotapocalypse.model.ResourcesEnum;
import com.survive.robotapocalypse.model.Survivor;
import com.survive.robotapocalypse.service.SurvivorService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    ResourcesRepository resourcesRepository;

    @MockBean
    MarkInfectedRepository markInfectedRepository;

    @BeforeAll
    public static void init() {

        survivorDTO = new SurvivorDTO();
        survivorDTO.setSurvivorId("1");
        survivorDTO.setAge(25);
        survivorDTO.setGender(GenderEnum.FEMALE);
        survivorDTO.setLatitude(20.89);
        survivorDTO.setLongitude(2.34);
        survivorDTO.setName("Srilekha");
        Set<ResourcesEnum> resourcesEnumSet = new HashSet<>();
        resourcesEnumSet.add(ResourcesEnum.WATER);
        resourcesEnumSet.add(ResourcesEnum.FOOD);
        survivorDTO.setResources(resourcesEnumSet);

    }

    @Test
    void TestSurvivorInsert() throws Exception {
        Survivor survivor = new Survivor();
        survivor.setSurvivorId("1");
        survivor.setAge(25);
        survivor.setGender(GenderEnum.FEMALE);
        survivor.setLatitude(20.89);
        survivor.setLongitude(2.34);
        survivor.setName("Srilekha");
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources(1L, "WATER"));
        resourcesSet.add(new Resources(2L, "FOOD"));
        survivor.setResources(resourcesSet);

        Mockito.when(survivorService.insert(any())).thenReturn(survivor);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/survivor")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(survivorDTO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.survivorId", notNullValue()));
    }

    @Test
    void TestSurvivorUpdate() throws Exception {
        Survivor survivor = new Survivor();
        survivor.setSurvivorId("1");
        survivor.setAge(25);
        survivor.setGender(GenderEnum.FEMALE);
        survivor.setLatitude(20.89);
        survivor.setLongitude(50.34);
        survivor.setName("Srilekha");
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources(1L, "WATER"));
        resourcesSet.add(new Resources(2L, "FOOD"));
        survivor.setResources(resourcesSet);

        Mockito.when(survivorService.update(any(), any())).thenReturn(survivor);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/survivor/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(survivorDTO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude", is(50.34)));
    }

    @Test
    void TestGetInfected() throws Exception {
        Survivor survivor = new Survivor();
        survivor.setSurvivorId("1");
        survivor.setAge(25);
        survivor.setGender(GenderEnum.FEMALE);
        survivor.setLatitude(20.89);
        survivor.setLongitude(50.34);
        survivor.setName("Srilekha");
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources(1L, "WATER"));
        resourcesSet.add(new Resources(2L, "FOOD"));
        survivor.setResources(resourcesSet);

        Survivor survivor1 = new Survivor();
        survivor1.setSurvivorId("2");
        survivor1.setAge(25);
        survivor1.setGender(GenderEnum.FEMALE);
        survivor1.setLatitude(20.89);
        survivor1.setLongitude(50.34);
        survivor1.setName("Srilekha");
        Set<Resources> resourcesSet1 = new HashSet<>();
        resourcesSet1.add(new Resources(1L, "WATER"));
        resourcesSet1.add(new Resources(2L, "FOOD"));
        survivor1.setResources(resourcesSet1);

        List<Survivor> survivorList = new ArrayList<Survivor>();
        survivorList.add(survivor);
        survivorList.add(survivor1);

        Mockito.when(survivorService.getInfectedSurvivors()).thenReturn(survivorList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/survivor/infected")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void TestGetInfectedPercent() throws Exception {
        Mockito.when(survivorService.getInfectedSurvivorsPercentage()).thenReturn(80.0);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/survivor/infected/percentage")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void TestGetNonInfected() throws Exception {
        Survivor survivor = new Survivor();
        survivor.setSurvivorId("1");
        survivor.setAge(25);
        survivor.setGender(GenderEnum.FEMALE);
        survivor.setLatitude(20.89);
        survivor.setLongitude(50.34);
        survivor.setName("Srilekha");
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources(1L, "WATER"));
        resourcesSet.add(new Resources(2L, "FOOD"));
        survivor.setResources(resourcesSet);

        Survivor survivor1 = new Survivor();
        survivor1.setSurvivorId("2");
        survivor1.setAge(25);
        survivor1.setGender(GenderEnum.FEMALE);
        survivor1.setLatitude(20.89);
        survivor1.setLongitude(50.34);
        survivor1.setName("Srilekha");
        Set<Resources> resourcesSet1 = new HashSet<>();
        resourcesSet1.add(new Resources(1L, "WATER"));
        resourcesSet1.add(new Resources(2L, "FOOD"));
        survivor1.setResources(resourcesSet1);

        List<Survivor> survivorList = new ArrayList<Survivor>();
        survivorList.add(survivor);
        survivorList.add(survivor1);

        Mockito.when(survivorService.getNonInfectedSurvivors()).thenReturn(survivorList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/survivor/non-infected")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void TestGetNonInfectedPercent() throws Exception {
        Mockito.when(survivorService.getInfectedSurvivorsPercentage()).thenReturn(80.0);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/survivor/non-infected/percentage")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

}
