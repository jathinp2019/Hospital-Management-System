package com.hsbc.service;

import com.hsbc.beans.Schedule;
import java.util.List;

public interface ScheduleService {
    void addSchedule(Schedule schedule);
    List<Schedule> getSchedulesByDoctor(int doctorId);
    void updateSchedule(Schedule schedule);
}
