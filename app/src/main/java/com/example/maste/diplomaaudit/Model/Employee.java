package com.example.maste.diplomaaudit.Model;

public class Employee {

    private int id;

    private String name;
    private String lastName;
    private String sureName;

    private String priorityHoliday;

    private boolean priorityMorning;
    private boolean priorityAfternoon;
    private boolean priorityEvening;

    private int minTime;
    private int maxTime;

    private String departmentName;

    public Employee() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSureName() {
        return sureName;
    }
    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPriorityHoliday() {
        return priorityHoliday;
    }
    public void setPriorityHoliday(String priorityHoliday) {
        this.priorityHoliday = priorityHoliday;
    }

    public boolean getPriorityMorning() {
        return priorityMorning;
    }
    public void setPriorityMorning(boolean priorityMorning) {
        this.priorityMorning = priorityMorning;
    }

    public boolean getPriorityAfternoon() {
        return priorityAfternoon;
    }
    public void setPriorityAfternoon(boolean priorityAfternoon) {
        this.priorityAfternoon = priorityAfternoon;
    }

    public boolean getPriorityEvening() {
        return priorityEvening;
    }
    public void setPriorityEvening(boolean priorityEvening) {
        this.priorityEvening = priorityEvening;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getMinTime() {
        return minTime;
    }
    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }




    private int convertBooleanToInteger(boolean input){
        if (input){
            return 1;
        } else {
            return 0;
        }
    }

}
