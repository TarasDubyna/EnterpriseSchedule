package com.example.maste.diplomaaudit.Schedule;


import com.example.maste.diplomaaudit.Model.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkShift {

    private Employee employee;
    private Date startWorkShiftTime;
    private Date endWorkShiftTime;

    public WorkShift() {

    }

    private ArrayList<String> convertPriorityHoliday(String stringPriorityHoliday){
        String[] parsedPriorityHoliday = stringPriorityHoliday.split(",");
        ArrayList<String> outputList = new ArrayList<>();
        for (int i = 0; i < outputList.size(); i++){
            outputList.add(parsedPriorityHoliday[i]);
        }
        return outputList;
    }

    private Date convertStringToDate(String stringTime){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        formatter.format(stringTime);
        return new Date(formatter.format(stringTime));
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartWorkShiftTime() {
        return startWorkShiftTime;
    }
    public void setStartWorkShiftTime(Date startWorkShiftTime) {
        this.startWorkShiftTime = startWorkShiftTime;
    }

    public Date getEndWorkShiftTime() {
        return endWorkShiftTime;
    }
    public void setEndWorkShiftTime(Date endWorkShiftTime) {
        this.endWorkShiftTime = endWorkShiftTime;
    }
}
