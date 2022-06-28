package com.survive.robotapocalypse.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import java.util.Set;

@Entity
public class Survivor {
    @Id
    @Column(name = "survivorId", nullable = false)
    private String survivorId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Age")
    private float age;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToMany
    @Column(name = "resources")
    @JoinTable(name = "Survivor_Resources",joinColumns = @JoinColumn(name = "survivorId"),
            inverseJoinColumns = @JoinColumn(name = "resourceId"))
    private Set<Resources> resources;

    @Transient
    private Set<ResourcesEnum> resourcesEnums;

    @Column(name = "infected" ,columnDefinition = "boolean default false")
    private boolean infected;

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

    public Set<Resources> getResources() {
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public Set<ResourcesEnum> getResourcesEnums() {
        return resourcesEnums;
    }

    public void setResourcesEnums(Set<ResourcesEnum> resourcesEnums) {
        this.resourcesEnums = resourcesEnums;
    }
}
