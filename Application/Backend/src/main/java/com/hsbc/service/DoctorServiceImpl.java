package com.hsbc.service;

import com.hsbc.beans.Doctor;
import com.hsbc.beans.Schedule;
import com.hsbc.dao.DoctorDao;
import com.hsbc.util.DaoFactory;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private DoctorDao doctorDao;

    public DoctorServiceImpl() {
        this.doctorDao = DaoFactory.getDoctorDao();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDao.addDoctor(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        doctorDao.addSchedule(schedule);
    }
}
