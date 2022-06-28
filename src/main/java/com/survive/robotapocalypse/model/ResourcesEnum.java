package com.survive.robotapocalypse.model;

public enum ResourcesEnum {

    WATER(1L,"WATER"),
    FOOD(2L,"FOOD"),
    MEDICATION(3L,"MEDICATION"),
    AMMUNITION(4L,"AMMUNITION");

    private final Long resourceId;
    private final String resourceName;

    ResourcesEnum(Long resourceId,String resourceName) {
        this.resourceId = resourceId; this.resourceName = resourceName;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }


}
