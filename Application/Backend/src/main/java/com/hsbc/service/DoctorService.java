package com.hsbc.service;

import com.hsbc.beans.Doctor;
import com.hsbc.beans.Schedule;
import java.util.List;

public interface DoctorService {
    void addDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    void addSchedule(Schedule schedule);
}
