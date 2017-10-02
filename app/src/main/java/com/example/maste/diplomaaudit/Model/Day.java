package com.example.maste.diplomaaudit.Model;


public class Day {

    private String dayName;
    private int dayNum;

    private String monthName;
    private int monthNum;

    private int yearNum;

    private String startDayTime;
    private String endDayTime;

    public Day() {

    }

    public Day(String dayName) {
        this.dayName = dayName;
    }

    public Day(String dayName, String startDayTime, String endDayTime) {
        this.dayName = dayName;
        this.startDayTime = startDayTime;
        this.endDayTime = endDayTime;
    }

    public Day(String startDayTime, String endDayTime){
        this.endDayTime = endDayTime;
        this.startDayTime = startDayTime;
    }

    public Day(String dayName, int dayNum, String monthName, int monthNum) {
        this.dayName = dayName;
        this.dayNum = dayNum;
        this.monthName = monthName;
        this.monthNum = monthNum;
    }

    public String getDayName() {
        return dayName;
    }
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartDayTime() {
        return startDayTime;
    }
    public void setStartDayTime(String startDayTime) {
        this.startDayTime = startDayTime;
    }

    public String getEndDayTime() {
        return endDayTime;
    }
    public void setEndDayTime(String endDayTime) {
        this.endDayTime = endDayTime;
    }

    public int getDayNum() {
        return dayNum;
    }
    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public String getMonthName() {
        return monthName;
    }
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthNum() {
        return monthNum;
    }
    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public int getYearNum() {
        return yearNum;
    }
    public void setYearNum(int yearNum) {
        this.yearNum = yearNum;
    }
}
