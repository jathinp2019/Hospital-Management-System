package com.hsbc.util;

import com.hsbc.dao.*;

public class DaoFactory {
    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static DoctorDao getDoctorDao() {
        return new DoctorDaoImpl();
    }

    public static PatientDao getPatientDao() {
        return new PatientDaoImpl();
    }

    public static AppointmentDao getAppointmentDao() {
        return new AppointmentDaoImpl();
    }

    public static ScheduleDao getScheduleDao() {
        return new ScheduleDaoImpl();
    }
}
