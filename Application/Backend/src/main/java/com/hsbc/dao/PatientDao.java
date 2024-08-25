package com.hsbc.dao;

import com.hsbc.beans.Patient;

import java.util.List;

public interface PatientDao {
    void addPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientByName(String name);
}
