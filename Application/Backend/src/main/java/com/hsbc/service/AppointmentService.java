package com.hsbc.service;

import com.hsbc.beans.Appointment;
import java.util.List;

public interface AppointmentService {
    void bookAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
    void cancelAppointment(int appointmentId);
}
