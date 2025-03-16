package com.example.cvmaker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Education implements Serializable {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;

    public Education(String institution, String degree, String fieldOfStudy, String startDate, String endDate) {
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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
    @NonNull
    @Override
    public String toString() {
        return degree + " in " + fieldOfStudy + " at " + institution + " (" + startDate + " - " + endDate + ")";
    }


}

