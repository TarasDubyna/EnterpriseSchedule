package com.example.maste.diplomaaudit.Model;


public class Department {

    private int id;
    private String name;
    private int numEmployees;
    private boolean allDayTime;
    private String enterpriseName;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
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

    public int getNumEmployees() {
        return numEmployees;
    }
    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public boolean getAllDayTime() {
        return allDayTime;
    }
    public void setAllDayTime(boolean allDayTime) {
        this.allDayTime = allDayTime;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
