package com.example.maste.diplomaaudit.Schedule;

import java.util.ArrayList;
import java.util.Date;

public class ScheduleDay {

    private Date startDayTime;
    private Date endDayTime;

    private boolean isHoliday;
    private boolean isAllDayAndNight;

    private ArrayList<WorkShift> workShiftList;

    public ScheduleDay() {
        workShiftList = new ArrayList<WorkShift>();
    }

    public Date getStartDayTime() {
        return startDayTime;
    }
    public void setStartDayTime(Date startDayTime) {
        this.startDayTime = startDayTime;
    }

    public Date getEndDayTime() {
        return endDayTime;
    }
    public void setEndDayTime(Date endDayTime) {
        this.endDayTime = endDayTime;
    }

    public boolean getHoliday() {
        return isHoliday;
    }
    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public boolean isAllDayAndNight() {
        return isAllDayAndNight;
    }
    public void setAllDayAndNight(boolean allDayAndNight) {
        isAllDayAndNight = allDayAndNight;
    }

    public ArrayList<WorkShift> getWorkShiftList() {
        return workShiftList;
    }
    public void setWorkShiftList(ArrayList<WorkShift> workShiftList) {
        this.workShiftList = workShiftList;
    }
}
