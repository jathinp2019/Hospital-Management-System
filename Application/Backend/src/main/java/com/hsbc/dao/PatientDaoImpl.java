package com.hsbc.dao;

import com.hsbc.beans.Patient;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patients (name, dob, contact_number) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patient.getName());
            ps.setDate(2, new java.sql.Date(patient.getDob().getTime()));
            ps.setString(3, patient.getContactNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patients";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setDob(rs.getDate("dob"));
                patient.setContactNumber(rs.getString("contact_number"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getPatientByName(String name) {
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE name = ?";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient();
                    patient.setPatientId(rs.getInt("id"));
                    patient.setName(rs.getString("name"));
                    patient.setDob(rs.getDate("dob"));
                    patient.setContactNumber(rs.getString("contact_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
