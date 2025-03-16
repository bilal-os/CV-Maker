package com.example.cvmaker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Certification implements Serializable {
    private String certificationName;
    private String issuingOrganization;
    private String issueDate;
    private String expiryDate;

    // Constructor
    public Certification(String certificationName, String issuingOrganization, String issueDate, String expiryDate) {
        this.certificationName = certificationName;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Override toString() for ListView
    @NonNull
    @Override
    public String toString() {
        return certificationName + " - " + issuingOrganization + " (" + issueDate + (expiryDate != null && !expiryDate.isEmpty() ? " to " + expiryDate : "") + ")";
    }
}