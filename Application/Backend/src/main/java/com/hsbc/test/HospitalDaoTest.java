package com.hsbc.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.hsbc.beans.*;
import com.hsbc.dao.*;
import com.hsbc.util.DbUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import com.hsbc.dao.AppointmentDaoImpl;
// other imports

public class HospitalDaoTest {

    private static Connection conn;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        conn = DbUtil.getConn();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Clean up tables before each test
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Appointments");
            stmt.execute("DELETE FROM Doctors");
            stmt.execute("DELETE FROM Patients");
            stmt.execute("DELETE FROM Users");
            stmt.execute("DELETE FROM Schedules");
        }
    }

    @Test
    public void testAddAndRetrievePatient() {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setDob(Date.valueOf("1990-01-01"));
        patient.setContactNumber("1234567890");

        patientDao.addPatient(patient);

        List<Patient> patients = patientDao.getAllPatients();
        assertEquals(1, patients.size());
        assertEquals("John Doe", patients.get(0).getName());
    }

    @Test
    public void testAddAndRetrieveDoctor() {
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialization("Cardiology");

        doctorDao.addDoctor(doctor);

        List<Doctor> doctors = doctorDao.getAllDoctors();
        assertEquals(1, doctors.size());
        assertEquals("Dr. Smith", doctors.get(0).getName());
    }

    @Test
    public void testAddAndRetrieveSchedule() {
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialization("Cardiology");

        doctorDao.addDoctor(doctor);
        List<Doctor> doctors = doctorDao.getAllDoctors();
        int doctorId = doctors.get(0).getDoctorId();

        Schedule schedule = new Schedule();
        schedule.setDoctorId(doctorId);
        schedule.setAvailableDate(Date.valueOf("2024-08-31"));
        schedule.setStartTime(Time.valueOf("09:00:00"));
        schedule.setEndTime(Time.valueOf("12:00:00"));

        doctorDao.addSchedule(schedule);

        // No direct method to retrieve schedule, so we'll have to check it manually from DB.
        String sql = "SELECT * FROM Schedules WHERE doctor_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(doctorId, rs.getInt("doctor_id"));
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testBookAndRetrieveAppointment() {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        PatientDao patientDao = new PatientDaoImpl();
        DoctorDao doctorDao = new DoctorDaoImpl();

        // Adding Patient
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setDob(Date.valueOf("1990-01-01"));
        patient.setContactNumber("1234567890");
        patientDao.addPatient(patient);
        int patientId = patientDao.getAllPatients().get(0).getPatientId();

        // Adding Doctor
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialization("Cardiology");
        doctorDao.addDoctor(doctor);
        int doctorId = doctorDao.getAllDoctors().get(0).getDoctorId();

        // Booking Appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(Date.valueOf("2024-09-01"));
        appointment.setStartTime(Time.valueOf("10:00:00"));
        appointment.setEndTime(Time.valueOf("10:30:00"));
        appointmentDao.bookAppointment(appointment);

        List<Appointment> appointments = appointmentDao.getAppointmentsByDoctor(doctorId);
        assertEquals(1, appointments.size());
        assertEquals(patientId, appointments.get(0).getPatientId());
        assertEquals(doctorId, appointments.get(0).getDoctorId());
    }

    @Test
    public void testAddAndAuthenticateUser() {
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("admin");

        userDao.addUser(user);

        User authenticatedUser = userDao.authenticate("testuser", "password123");
        assertNotNull(authenticatedUser);
        assertEquals("testuser", authenticatedUser.getUsername());
    }
}
