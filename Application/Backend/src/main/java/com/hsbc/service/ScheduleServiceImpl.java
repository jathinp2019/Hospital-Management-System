package com.hsbc.service;

import com.hsbc.beans.Schedule;
import com.hsbc.dao.ScheduleDao;
import com.hsbc.util.DaoFactory;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleDao scheduleDao;

    public ScheduleServiceImpl() {
        this.scheduleDao = DaoFactory.getScheduleDao();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleDao.addSchedule(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDoctor(int doctorId) {
        return scheduleDao.getSchedulesByDoctor(doctorId);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleDao.updateSchedule(schedule);
    }
}
