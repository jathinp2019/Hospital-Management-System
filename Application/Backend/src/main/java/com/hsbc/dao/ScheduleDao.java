package com.hsbc.dao;

import com.hsbc.beans.Schedule;

import java.util.List;

public interface ScheduleDao {
    void addSchedule(Schedule schedule);
    List<Schedule> getSchedulesByDoctor(int doctorId);
    void updateSchedule(Schedule schedule);
}
