package com.hsbc.service;

import com.hsbc.beans.Patient;
import com.hsbc.dao.PatientDao;
import com.hsbc.util.DaoFactory;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private PatientDao patientDao;

    public PatientServiceImpl() {
        this.patientDao = DaoFactory.getPatientDao();
    }

    @Override
    public void addPatient(Patient patient) {
        patientDao.addPatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    @Override
    public Patient getPatientByName(String name) {
        return patientDao.getPatientByName(name);
    }
}
