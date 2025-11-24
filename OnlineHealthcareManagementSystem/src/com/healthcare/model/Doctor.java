// File: src/com/healthcare/model/Doctor.java
package com.healthcare.model;

public class Doctor {
    private int id;
    private int userId;
    private String specialization;
    private int experienceYears;
    private String bio;

    public Doctor() {}

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) { this.userId = userId; }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) { this.bio = bio; }
}
