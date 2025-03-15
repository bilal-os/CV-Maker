package com.example.cvmaker;

import android.net.Uri;

public class CVInformation {
    private Uri uriProfilePicture = null;
    private String fullName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String address;

    private String summary;

    public String getSummary()
    {
        return summary;
    }
    public void setSummary(String summary)
    {
        this.summary=summary;
    }
    public Uri getUriProfilePicture() {
        return uriProfilePicture;
    }

    public void setUriProfilePicture(Uri uriProfilePicture) {
        this.uriProfilePicture = uriProfilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
