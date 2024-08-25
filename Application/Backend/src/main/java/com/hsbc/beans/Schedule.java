package com.hsbc.beans;

import java.util.Date;

public class Schedule {
    private int scheduleId;
    private int doctorId;
    private Date availableDate;
    private Date startTime;
    private Date endTime;

    // Getters and setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule [scheduleId=" + scheduleId + ", doctorId=" + doctorId + ", availableDate=" + availableDate +
                ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
}
