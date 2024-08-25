package com.hsbc.dao;

import com.hsbc.beans.Appointment;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    @Override
    public void bookAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            ps.setTime(4, new java.sql.Time(appointment.getStartTime().getTime()));
            ps.setTime(5, new java.sql.Time(appointment.getEndTime().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments WHERE doctor_id = ?";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setStartTime(rs.getTime("start_time"));
                appointment.setEndTime(rs.getTime("end_time"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public void cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM Appointments WHERE appointment_id = ?";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
