package com.example.maste.diplomaaudit.Schedule;


import com.example.maste.diplomaaudit.Model.Day;

public class PairKeyValue {

    private Day key;
    private ScheduleDay value;

    public PairKeyValue() {
    }
    public PairKeyValue(Day key, ScheduleDay value) {
        this.key = key;
        this.value = value;
    }

    public Day getKey() {
        return key;
    }
    public void setKey(Day key) {
        this.key = key;
    }

    public ScheduleDay getValue() {
        return value;
    }
    public void setValue(ScheduleDay value) {
        this.value = value;
    }
}
