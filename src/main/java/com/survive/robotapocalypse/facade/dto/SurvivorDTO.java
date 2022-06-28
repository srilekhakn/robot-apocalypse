package com.survive.robotapocalypse.facade.dto;

import com.survive.robotapocalypse.model.GenderEnum;
import com.survive.robotapocalypse.model.Resources;
import com.survive.robotapocalypse.model.ResourcesEnum;
import com.survive.robotapocalypse.model.Survivor;

import java.util.HashSet;
import java.util.Set;

public class SurvivorDTO {

    private String survivorId;

    private String name;

    private float age;

    private GenderEnum gender;

    private double latitude;

    private double longitude;

    private Set<ResourcesEnum> resources;

    public String getSurvivorId() {
        return survivorId;
    }

    public void setSurvivorId(String survivorId) {
        this.survivorId = survivorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Set<ResourcesEnum> getResources() {
        return resources;
    }

    public void setResources(Set<ResourcesEnum> resources) {
        this.resources = resources;
    }

    public static Survivor toSurvivor(final SurvivorDTO dto) {
        final Survivor s = new Survivor();
        s.setSurvivorId(dto.getSurvivorId());
        s.setName(dto.getName());
        s.setAge(dto.getAge());
        s.setGender(dto.gender);

        if(dto.getLatitude() < -90 || dto.getLatitude() > 90){
            throw new IllegalArgumentException("Invalid Latitude");
        }

        if(dto.getLongitude() < -180 || dto.getLongitude() > 180){
            throw new IllegalArgumentException("Invalid Longitude");
        }
        s.setLongitude(dto.getLongitude());
        s.setLatitude(dto.getLatitude());
        s.setInfected(false);
        s.setResourcesEnums(dto.getResources());
        return s;
    }

    public static SurvivorDTO fromSurvivor(final Survivor survivor) {
        final SurvivorDTO s = new SurvivorDTO();
        s.setSurvivorId(survivor.getSurvivorId());
        s.setName(survivor.getName());
        s.setAge(survivor.getAge());
        s.setGender(survivor.getGender());
        s.setLongitude(survivor.getLongitude());
        s.setLatitude(survivor.getLatitude());
        Set<Resources> resourcesSet = survivor.getResources();
        Set<ResourcesEnum> resourcesEnumSet = new HashSet<>();
        if(null != resourcesSet) {
            for (Resources re : resourcesSet) {
                switch (re.getName().toUpperCase()) {
                    case "WATER":
                        resourcesEnumSet.add(ResourcesEnum.WATER);
                        break;
                    case "FOOD":
                        resourcesEnumSet.add(ResourcesEnum.FOOD);
                        break;
                    case "AMMUNITION":
                        resourcesEnumSet.add(ResourcesEnum.AMMUNITION);
                        break;
                    case "MEDICATION":
                        resourcesEnumSet.add(ResourcesEnum.MEDICATION);
                        break;
                    default:
                        break;
                }
            }
        }
        s.setResources(resourcesEnumSet);
        return s;
    }
}
