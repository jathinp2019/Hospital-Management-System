package com.hsbc.dao;

import com.hsbc.beans.Appointment;

import java.util.List;

public interface AppointmentDao {
    void bookAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
    void cancelAppointment(int appointmentId);
}
