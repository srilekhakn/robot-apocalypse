package com.survive.robotapocalypse.service;

import com.survive.robotapocalypse.dao.MarkInfectedRepository;
import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.dao.SurvivorRepository;
import com.survive.robotapocalypse.model.GenderEnum;
import com.survive.robotapocalypse.model.Resources;
import com.survive.robotapocalypse.model.ResourcesEnum;
import com.survive.robotapocalypse.model.Survivor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(SurvivorService.class)
class SurvivorServiceTest {

    private static Survivor survivor;
    @MockBean
    SurvivorRepository survivorRepository;
    @MockBean
    ResourcesRepository resourcesRepository;
    @MockBean
    MarkInfectedRepository markInfectedRepository;
    @Autowired
    private SurvivorService survivorService;

    @BeforeAll
    public static void init() {
        survivor = new Survivor();
        survivor.setSurvivorId("1");
        survivor.setAge(25);
        survivor.setGender(GenderEnum.FEMALE);
        survivor.setLatitude(20.89);
        survivor.setLongitude(2.34);
        survivor.setName("Srilekha");
        Set<ResourcesEnum> resourcesEnumSet = new HashSet<>();
        resourcesEnumSet.add(ResourcesEnum.WATER);
        resourcesEnumSet.add(ResourcesEnum.FOOD);
        survivor.setResourcesEnums(resourcesEnumSet);
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources(1L, "WATER"));
        resourcesSet.add(new Resources(2L, "FOOD"));
        survivor.setResources(resourcesSet);
    }

    @Test
    @DisplayName("JUnit test for Survivor Insert method")
    void TestSurvivorInsert() throws Exception {

        //    Test Survivor Insert in service class
        Mockito.when(survivorRepository.existsBySurvivorId(any())).thenReturn(false);
        Mockito.when(survivorRepository.save(any())).thenReturn(survivor);
        Mockito.when(survivorRepository.findById(any())).thenReturn(Optional.of(survivor));

        Survivor survivor1 = survivorService.insert(survivor);

        assertNotNull(survivor1);
    }

    @Test
    @DisplayName("JUnit test for Survivor update method")
    void TestSurvivorUpdate() throws Exception {
        //    Test Survivor Update in service class

        survivor.setLongitude(12.0);

        //    Test Survivor Update in service class
        Mockito.when(survivorRepository.existsBySurvivorId(any())).thenReturn(false);
        Mockito.when(survivorRepository.save(any())).thenReturn(survivor);
        Mockito.when(survivorRepository.findById(any())).thenReturn(Optional.of(survivor));

        Survivor survivor1 = survivorService.update("1", survivor);

        assertNotNull(survivor1);
        assertEquals(12.0, survivor1.getLongitude());
    }

    @Test
    @DisplayName("JUnit test for Survivor getInfected method")
    void TestSurvivorGetInfected() throws Exception {
        //    Test Survivor getInfected in service class
        List<Survivor> survivorList = new ArrayList<>();
        survivorList.add(survivor);
        Mockito.when(survivorRepository.findByInfected(true)).thenReturn(survivorList);
        List<Survivor> survivorList1 = survivorService.getInfectedSurvivors();
        assertEquals(1, survivorList1.size());
    }

    @Test
    @DisplayName("JUnit test for Survivor getNonInfected method")
    void TestSurvivorGetNonInfected() throws Exception {
        //    Test Survivor getNonInfected in service class
        List<Survivor> survivorList = new ArrayList<>();
        survivorList.add(survivor);
        Mockito.when(survivorRepository.findByInfected(false)).thenReturn(survivorList);
        List<Survivor> survivorList1 = survivorService.getNonInfectedSurvivors();
        assertEquals(1, survivorList1.size());
    }

    @Test
    @DisplayName("JUnit test for Survivor getInfected percent method")
    void TestSurvivorGetInfectedPercent() throws Exception {

        Mockito.when(survivorRepository.countByInfected(true)).thenReturn(30L);
        Mockito.when(survivorRepository.count()).thenReturn(100L);

        //    Test Survivor getInfectedPercent in service class
        double percent = survivorService.getInfectedSurvivorsPercentage();
        assertEquals(30.0, percent);
    }

    @Test
    @DisplayName("JUnit test for Survivor getNonInfected percent method")
    void TestSurvivorGetNonInfectedPercent() throws Exception {

        Mockito.when(survivorRepository.countByInfected(false)).thenReturn(30L);
        Mockito.when(survivorRepository.count()).thenReturn(100L);

        //    Test Survivor getNonInfectedPercent in service class
        double percent = survivorService.getNonInfectedSurvivorsPercentage();
        assertEquals(30.0, percent);
    }

}