package com.hsbc.dao;

import com.hsbc.beans.Doctor;
import com.hsbc.beans.Schedule;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (name, specialization) VALUES (?, ?)";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO Schedules (doctor_id, available_date, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, schedule.getDoctorId());
            ps.setDate(2, new java.sql.Date(schedule.getAvailableDate().getTime()));
            ps.setTime(3, new java.sql.Time(schedule.getStartTime().getTime()));
            ps.setTime(4, new java.sql.Time(schedule.getEndTime().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
