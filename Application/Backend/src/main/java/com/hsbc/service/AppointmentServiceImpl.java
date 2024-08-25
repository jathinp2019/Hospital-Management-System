package com.hsbc.service;

import com.hsbc.beans.Appointment;
import com.hsbc.dao.AppointmentDao;
import com.hsbc.util.DaoFactory;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentDao appointmentDao;

    public AppointmentServiceImpl() {
        this.appointmentDao = DaoFactory.getAppointmentDao();
    }

    @Override
    public void bookAppointment(Appointment appointment) {
        appointmentDao.bookAppointment(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        return appointmentDao.getAppointmentsByDoctor(doctorId);
    }

    @Override
    public void cancelAppointment(int appointmentId) {
        appointmentDao.cancelAppointment(appointmentId);
    }
}
