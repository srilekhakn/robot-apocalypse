package com.survive.robotapocalypse.model;

/**
 * Enumeration that holds possible types of Gender.
 */
public enum GenderEnum {
    FEMALE("F"),
    MALE("M"),
    OTHERS("O");
    private final String gender;

    GenderEnum(final String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }
}
