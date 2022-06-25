package com.survive.robotapocalypse.model;

/**
 * Enumeration that holds possible types of Gender.
 */
public enum Gender {
    FEMALE("F"),
    MALE("M"),
    OTHERS("O");
    private final String gender;

    Gender(final String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }
}
