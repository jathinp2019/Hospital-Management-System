package com.hsbc.service;

import com.hsbc.beans.Patient;
import java.util.List;

public interface PatientService {
    void addPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientByName(String name);
}
