package com.hsbc.beans;

import java.util.Date;

public class Patient {
    private int patientId;
    private String name;
    private Date dob;
    private String contactNumber;

    // Getters and setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", name=" + name + ", dob=" + dob + ", contactNumber=" + contactNumber + "]";
    }
}
