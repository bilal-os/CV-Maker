package com.example.cvmaker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Experience implements Serializable {
    private String companyName;
    private String role;
    private String startDate;
    private String endDate;
    private String responsibilities;

    // Constructor
    public Experience(String companyName, String role, String startDate, String endDate, String responsibilities) {
        this.companyName = companyName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsibilities = responsibilities;
    }

    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    // toString Method for Display
    @NonNull
    @Override
    public String toString() {
        return role + " at " + companyName + " (" + startDate + " - " + (endDate.isEmpty() ? "Present" : endDate) + ")";
    }
}
