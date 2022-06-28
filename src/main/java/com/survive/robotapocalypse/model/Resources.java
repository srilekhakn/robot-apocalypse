package com.survive.robotapocalypse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resources {

    @Id
    @Column(name = "resourceId", nullable = false)
    private Long resourceId;

    @Column(name = "name", nullable = false)
    private String name;

    public Resources() {
    }

    public Long getId() {
        return resourceId;
    }

    public void setId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resources(Long resourceId, String name) {
        this.resourceId = resourceId;
        this.name = name;
    }
}
