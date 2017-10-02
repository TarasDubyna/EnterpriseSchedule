package com.example.maste.diplomaaudit.Model;

import java.util.ArrayList;
import java.util.Date;


public class Enterprise {

    private int id;
    private String name;
    private ArrayList<Day> days = new ArrayList<>();

    private Date mondayStartTime;
    private Date mondayEndTime;
    private Date tuesdayStartTime;
    private Date tuesdayEndTime;
    private Date wednesdayStartTime;
    private Date wednesdayEndTime;
    private Date thursdayStartTime;
    private Date thursdayEndTime;
    private Date fridayStartTime;
    private Date fridayEndTime;
    private Date saturdayStartTime;
    private Date saturdayEndTime;
    private Date sundayStartTime;
    private Date sundayEndTime;


    String[] daysNames = {"Monday","Tuesday","Wednesday","Thursday", "Friday", "Saturday", "Sunday"};

    public Enterprise() {
        for (int i = 0; i <= 6; i++){
            Day day = new Day();
            day.setDayName(daysNames[i]);
            days.add(i,day);
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Day> getDays() {
        return days;
    }
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }



    public Date getMondayStartTime() {
        return mondayStartTime;
    }
    public void setMondayStartTime(Date mondayStartTime) {
        this.mondayStartTime = mondayStartTime;
    }

    public Date getMondayEndTime() {
        return mondayEndTime;
    }
    public void setMondayEndTime(Date mondayEndTime) {
        this.mondayEndTime = mondayEndTime;
    }

    public Date getTuesdayStartTime() {
        return tuesdayStartTime;
    }
    public void setTuesdayStartTime(Date tuesdayStartTime) {
        this.tuesdayStartTime = tuesdayStartTime;
    }

    public Date getTuesdayEndTime() {
        return tuesdayEndTime;
    }
    public void setTuesdayEndTime(Date tuesdayEndTime) {
        this.tuesdayEndTime = tuesdayEndTime;
    }

    public Date getWednesdayStartTime() {
        return wednesdayStartTime;
    }
    public void setWednesdayStartTime(Date wednesdayStartTime) {
        this.wednesdayStartTime = wednesdayStartTime;
    }

    public Date getWednesdayEndTime() {
        return wednesdayEndTime;
    }
    public void setWednesdayEndTime(Date wednesdayEndTime) {
        this.wednesdayEndTime = wednesdayEndTime;
    }

    public Date getThursdayStartTime() {
        return thursdayStartTime;
    }
    public void setThursdayStartTime(Date thursdayStartTime) {
        this.thursdayStartTime = thursdayStartTime;
    }

    public Date getThursdayEndTime() {
        return thursdayEndTime;
    }
    public void setThursdayEndTime(Date thursdayEndTime) {
        this.thursdayEndTime = thursdayEndTime;
    }

    public Date getFridayStartTime() {
        return fridayStartTime;
    }
    public void setFridayStartTime(Date fridayStartTime) {
        this.fridayStartTime = fridayStartTime;
    }

    public Date getFridayEndTime() {
        return fridayEndTime;
    }
    public void setFridayEndTime(Date fridayEndTime) {
        this.fridayEndTime = fridayEndTime;
    }

    public Date getSaturdayStartTime() {
        return saturdayStartTime;
    }
    public void setSaturdayStartTime(Date saturdayStartTime) {
        this.saturdayStartTime = saturdayStartTime;
    }

    public Date getSaturdayEndTime() {
        return saturdayEndTime;
    }
    public void setSaturdayEndTime(Date saturdayEndTime) {
        this.saturdayEndTime = saturdayEndTime;
    }

    public Date getSundayStartTime() {
        return sundayStartTime;
    }
    public void setSundayStartTime(Date sundayStartTime) {
        this.sundayStartTime = sundayStartTime;
    }

    public Date getSundayEndTime() {
        return sundayEndTime;
    }
    public void setSundayEndTime(Date sundayEndTime) {
        this.sundayEndTime = sundayEndTime;
    }
}
