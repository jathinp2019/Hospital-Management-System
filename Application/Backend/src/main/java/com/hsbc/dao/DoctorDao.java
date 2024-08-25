package com.hsbc.dao;

import com.hsbc.beans.Doctor;
import com.hsbc.beans.Schedule;

import java.util.List;

public interface DoctorDao {
    void addDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    void addSchedule(Schedule schedule);
}
