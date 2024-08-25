package com.hsbc.util;

import com.hsbc.service.*;

public class ServiceFactory {
    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static DoctorService getDoctorService() {
        return new DoctorServiceImpl();
    }

    public static PatientService getPatientService() {
        return new PatientServiceImpl();
    }

    public static AppointmentService getAppointmentService() {
        return new AppointmentServiceImpl();
    }

    public static ScheduleService getScheduleService() {
        return new ScheduleServiceImpl();
    }
}
