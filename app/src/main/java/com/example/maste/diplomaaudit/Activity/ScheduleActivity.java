package com.example.maste.diplomaaudit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;


import com.example.maste.diplomaaudit.Adapters.ScheduleAdapter;
import com.example.maste.diplomaaudit.DB.DatabaseHelper;
import com.example.maste.diplomaaudit.Model.Day;
import com.example.maste.diplomaaudit.Model.Department;
import com.example.maste.diplomaaudit.Model.Employee;
import com.example.maste.diplomaaudit.Model.Enterprise;
import com.example.maste.diplomaaudit.Model.PairDate;
import com.example.maste.diplomaaudit.R;
import com.example.maste.diplomaaudit.Schedule.PairKeyValue;
import com.example.maste.diplomaaudit.Schedule.ScheduleDay;
import com.example.maste.diplomaaudit.Schedule.WorkShift;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class ScheduleActivity extends AppCompatActivity {

    ArrayList<PairKeyValue> monthScheduleList;
    ArrayList<Employee> employeesOfDepartmentList;

    Button nextMonth;
    Button prevMonth;
    TextView currentMonth;
    TextView currentYear;

    Enterprise enterprise;
    Department department;

    String enterpriseName;
    String departmentName;

    ExpandableListView scheduleListView;
    TextView departmentNameTextView;

    ArrayList<PairKeyValue> createMonthSchedule;
    ArrayList<ArrayList<PairKeyValue>> monthList = new ArrayList<>();

    int month = 0;
    int year = 0;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent = getIntent();
        departmentName = intent.getStringExtra("department_name");
        enterpriseName = intent.getStringExtra("enterprise_name");

        scheduleListView = (ExpandableListView) findViewById(R.id.schedule_list_view);
        departmentNameTextView = (TextView) findViewById(R.id.employee_department_name);

        nextMonth = (Button) findViewById(R.id.next_month_button);
        prevMonth = (Button) findViewById(R.id.previous_month_button);
        currentMonth = (TextView) findViewById(R.id.current_month_text);
        currentYear = (TextView) findViewById(R.id.current_year_text);

        departmentNameTextView.setText(departmentName);

        month = getNumCurrentMonth() + 1;
        year = getCurrentYear();
        currentMonth.setText(convertWordByLanguage(month));
        currentYear.setText(String.valueOf(year));
        nextMonth.setText(convertWordByLanguage(month + 1));
        prevMonth.setText(convertWordByLanguage(month - 1));

        if (position == 0){
            prevMonth.setVisibility(View.INVISIBLE);
        }


        createMonthSchedule = createMonthSchedule(enterpriseName, departmentName, month, year);
        monthList.add(position, createMonthSchedule);
        ScheduleAdapter adapter = new ScheduleAdapter(createMonthSchedule, this);
        scheduleListView.setAdapter(adapter);
        for (int i = 0; i < createMonthSchedule(enterpriseName, departmentName, month, year).size(); i++){
            scheduleListView.expandGroup(i);
        }

        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month++;
                position++;
                if (month > 12){
                    month = 1;
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(month + 1));
                    prevMonth.setText(convertWordByLanguage(12));
                    year++;
                    currentYear.setText(String.valueOf(year));
                } else if (month == 12){
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(1));
                    prevMonth.setText(convertWordByLanguage(month - 1));
                    currentYear.setText(String.valueOf(year));
                } else {
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(month + 1));
                    prevMonth.setText(convertWordByLanguage(month - 1));
                    currentYear.setText(String.valueOf(year));
                }

                if (position > 0){
                    prevMonth.setVisibility(View.VISIBLE);
                }
                if (position < 1){
                    prevMonth.setVisibility(View.INVISIBLE);
                }
                if (monthList.size() <= position){
                    prevMonth.setClickable(true);
                    monthList.add(createMonthSchedule(enterpriseName, departmentName, month, year));
                }
            }
        });

        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month--;
                position--;
                if (month < 1){
                    month = 12;
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(1));
                    prevMonth.setText(convertWordByLanguage(month - 1));
                    year--;
                    currentYear.setText(String.valueOf(year));
                } else if (month == 1){
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(month + 1));
                    prevMonth.setText(convertWordByLanguage(12));
                    currentYear.setText(String.valueOf(year));
                } else {
                    currentMonth.setText(convertWordByLanguage(month));
                    nextMonth.setText(convertWordByLanguage(month + 1));
                    prevMonth.setText(convertWordByLanguage(month - 1));
                    currentYear.setText(String.valueOf(year));
                }

                //ДОДЕЛАТЬ
                if (position > 0){
                    prevMonth.setVisibility(View.VISIBLE);
                }
                if (position < 1){
                    prevMonth.setVisibility(View.INVISIBLE);
                }
                if (monthList.size() <= position){
                    prevMonth.setClickable(true);
                    monthList.add(createMonthSchedule(enterpriseName, departmentName, month, year));
                }
            }
        });
    }


    //Ограничения по закону
    private static final int LIMIT_MAX_HOUR_IN_WEEK = 40;//МАКСИМАЛЬНОЕ КОЛИЧЕСТВО ЧАСОВ В НЕДЕЛЮ
    private static final int LIMIT_6_DAY_WEEK_HOUR_IN_DAY = 6;//ПРИ РАБОЧИХ 6 ДНЯХ ИЗ 7
    private static final int LIMIT_ALL_DAY_NIGHT_DAY = 2;//СУТКИ ЧЕРЕЗ 2

    private ArrayList<PairKeyValue> createMonthSchedule(String enterpriseName, String departmentName, int month, int year){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        employeesOfDepartmentList = new ArrayList<>(databaseHelper.getAllEmployees(departmentName));
        enterprise = databaseHelper.getEnterpriseInformation(enterpriseName);
        department = databaseHelper.getDepartmentInformation(departmentName);

        monthScheduleList = createMonthScheduleList(month, year);

        //Предварительное заполнение расписания дня(без персонала)
        for (int i = 0; i < monthScheduleList.size(); i++){
            if (monthScheduleList.get(i).getValue() == null){
                ScheduleDay scheduleDay = new ScheduleDay();
                scheduleDay.setAllDayAndNight(department.getAllDayTime());
                scheduleDay.setStartDayTime(getTimeOfSameDay(enterprise,true, monthScheduleList.get(i).getKey().getDayName()));
                scheduleDay.setEndDayTime(getTimeOfSameDay(enterprise,false, monthScheduleList.get(i).getKey().getDayName()));
                scheduleDay.setHoliday(checkIsHoliday(enterprise, monthScheduleList.get(i).getKey().getDayName()));
                scheduleDay.setWorkShiftList(new ArrayList<WorkShift>());
                monthScheduleList.get(i).setValue(scheduleDay);
            }
        }

        //Заполнение рассписания персоналом
        for (Employee employee : employeesOfDepartmentList){
            for (int i = 0; i < monthScheduleList.size(); i++){
                if (department.getAllDayTime()){
                    System.out.println("Test");
                } else {
                    if (checkHolidayPriorityForEmployee(employee, monthScheduleList.get(i).getKey().getDayName())){
                        WorkShift workShift = new WorkShift();
                        workShift.setEmployee(employee);
                        workShift.setStartWorkShiftTime(getTimePartsOfDay(getTimeOfSameDay(enterprise, true, monthScheduleList.get(i).getKey().getDayName()),
                                getTimeOfSameDay(enterprise, false, monthScheduleList.get(i).getKey().getDayName()),employee).getStartDate());
                        workShift.setEndWorkShiftTime(getTimePartsOfDay(getTimeOfSameDay(enterprise, true, monthScheduleList.get(i).getKey().getDayName()),
                                getTimeOfSameDay(enterprise, false, monthScheduleList.get(i).getKey().getDayName()),employee).getEndDate());
                        monthScheduleList.get(i).getValue().getWorkShiftList().add(workShift);
                        System.out.println("sdfsdfdsf");
                    }

                }
            }
        }
        return monthScheduleList;
    }


    private int getNumCurrentMonth(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    private int getCurrentYear(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private int convertWordByLanguage(int numMonth){

        HashMap<Integer, Integer> languageMap = new HashMap<>();
        languageMap.put(1, R.string.january);
        languageMap.put(2, R.string.february);
        languageMap.put(3, R.string.march);
        languageMap.put(4, R.string.april);
        languageMap.put(5, R.string.may);
        languageMap.put(6, R.string.june);
        languageMap.put(7, R.string.july);
        languageMap.put(8, R.string.august);
        languageMap.put(9, R.string.september);
        languageMap.put(10, R.string.october);
        languageMap.put(11, R.string.november);
        languageMap.put(12, R.string.december);


        for (HashMap.Entry entry: languageMap.entrySet()){
            if (entry.getKey().equals(numMonth)){
                return (int) entry.getValue();
            }
        }
        return 0;
    }


    /*
    private Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    private int getNumDayOfCurrentMonth(){
        Date date = getCurrentDate();
        Calendar mycal = new GregorianCalendar(date.getYear(),date.getMonth(),date.getDay());
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    */

    private Day setDayByDayMonthYear(int iDay, int iMonth, int iYear){
        Day day = new Day();
        String dateString = String.format("%d-%d-%d", iDay, iMonth, iYear);
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            day.setDayName(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(newDate));
            day.setDayNum(iDay);
            day.setMonthName(new DateFormatSymbols(Locale.ENGLISH).getMonths()[iMonth - 1]);
            day.setMonthNum(iMonth);
            day.setYearNum(iYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    //Создание пустого календаря
    private ArrayList<PairKeyValue> createMonthScheduleList(int month, int year){
        ArrayList<PairKeyValue> monthScheduleList = new ArrayList<>();
        Calendar mycal = new GregorianCalendar(year, month - 1, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInMonth; i++){
            monthScheduleList.add(new PairKeyValue(setDayByDayMonthYear(i, month, year), null));
        }
        return monthScheduleList;
    }

    //Взятие начала/конца робочего времени по дням недели
    private Date getTimeOfSameDay(Enterprise enterprise, boolean isStartDate, String dayName){
        Date date = new Date();
        switch(dayName) {
            case "Monday":
                if (isStartDate){
                    date = enterprise.getMondayStartTime();
                } else {
                    date = enterprise.getMondayEndTime();
                }
                break;
            case "Thursday":
                if (isStartDate){
                    date = enterprise.getThursdayStartTime();
                } else {
                    date = enterprise.getThursdayEndTime();
                }
                break;
            case "Wednesday":
                if (isStartDate){
                    date = enterprise.getWednesdayStartTime();
                } else {
                    date = enterprise.getWednesdayEndTime();
                }
                break;
            case "Tuesday":
                if (isStartDate){
                    date = enterprise.getTuesdayStartTime();
                } else {
                    date = enterprise.getTuesdayEndTime();
                }
                break;
            case "Friday":
                if (isStartDate){
                    date = enterprise.getFridayStartTime();
                } else {
                    date = enterprise.getFridayEndTime();
                }
                break;
            case "Saturday":
                if (isStartDate){
                    date = enterprise.getSaturdayStartTime();
                } else {
                    date = enterprise.getSaturdayEndTime();
                }
                break;
            case "Sunday":
                if (isStartDate){
                    date = enterprise.getSundayStartTime();
                } else {
                    date = enterprise.getSundayStartTime();
                }
                break;
            default:
                break;
        }
        return date;
    }

    //Проверка, является ли день выходным для предприятия
    private boolean checkIsHoliday(Enterprise enterprise, String dayName){
        ArrayList<Day> days = new ArrayList<>();

        days.add(new Day("Monday",convertTimeToString(enterprise.getMondayStartTime()), convertTimeToString(enterprise.getMondayEndTime())));
        days.add(new Day("Tuesday",convertTimeToString(enterprise.getTuesdayStartTime()), convertTimeToString(enterprise.getTuesdayEndTime())));
        days.add(new Day("Wednesday",convertTimeToString(enterprise.getWednesdayStartTime()), convertTimeToString(enterprise.getWednesdayEndTime())));
        days.add(new Day("Thursday",convertTimeToString(enterprise.getThursdayStartTime()), convertTimeToString(enterprise.getThursdayEndTime())));
        days.add(new Day("Friday",convertTimeToString(enterprise.getFridayStartTime()), convertTimeToString(enterprise.getFridayEndTime())));
        days.add(new Day("Saturday",convertTimeToString(enterprise.getSaturdayStartTime()), convertTimeToString(enterprise.getSaturdayEndTime())));
        days.add(new Day("Sunday",convertTimeToString(enterprise.getSundayStartTime()), convertTimeToString(enterprise.getSundayEndTime())));

        for (Day day: days){
            if (day.getDayName().equals(dayName)){
                if (day.getStartDayTime() == null || day.getEndDayTime() == null){
                    return true;
                }
            }
        }
        return false;
    }

    //Определение времени работы при приоритете (утро,день,вечер)
    private PairDate getTimePartsOfDay(Date startTimeDay, Date endTimeDay, Employee employee){
        long milliseconds = endTimeDay.getTime() - startTimeDay.getTime();
        int hours = (int) (milliseconds/(60 * 60 * 1000));
        int minutes = (int) (milliseconds / (60 * 1000) - (hours * 60));
        int partOfTimeHours = 0;
        int partOfTimeMinutes = 0;

        if (hours != 0){
            partOfTimeHours = hours/3;
        } else {
            partOfTimeHours = 0;
        }
        if (minutes != 0){
            partOfTimeMinutes = minutes/3;
        } else {
            partOfTimeMinutes = 0;
        }

        PairDate pairDate = new PairDate();

        if (employee.getPriorityMorning() && employee.getPriorityAfternoon() && employee.getPriorityEvening()){
            pairDate.setStartDate(startTimeDay);
            pairDate.setEndDate(endTimeDay);
            return pairDate;
        }
        if (employee.getPriorityMorning() && !employee.getPriorityAfternoon() && !employee.getPriorityEvening()){
            pairDate.setStartDate(startTimeDay);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, partOfTimeHours);
            cal.add(Calendar.MINUTE, partOfTimeMinutes);
            pairDate.setEndDate(cal.getTime());
            return pairDate;
        }
        if (employee.getPriorityMorning() && employee.getPriorityAfternoon() && !employee.getPriorityEvening()){
            pairDate.setStartDate(startTimeDay);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, 2*partOfTimeHours);
            cal.add(Calendar.MINUTE, 2* partOfTimeMinutes);
            pairDate.setEndDate(cal.getTime());
            return pairDate;
        }
        if (!employee.getPriorityMorning() && employee.getPriorityAfternoon() && !employee.getPriorityEvening()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, partOfTimeHours);
            cal.add(Calendar.MINUTE, partOfTimeMinutes);
            pairDate.setStartDate(cal.getTime());
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, 2*partOfTimeHours);
            cal.add(Calendar.MINUTE, 2* partOfTimeMinutes);
            pairDate.setEndDate(cal.getTime());
            return pairDate;
        }
        if (!employee.getPriorityMorning() && employee.getPriorityAfternoon() && employee.getPriorityEvening()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, partOfTimeHours);
            cal.add(Calendar.MINUTE, partOfTimeMinutes);
            pairDate.setStartDate(cal.getTime());
            pairDate.setEndDate(endTimeDay);
            return pairDate;
        }
        if (!employee.getPriorityMorning() && !employee.getPriorityAfternoon() && employee.getPriorityEvening()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTimeDay);
            cal.add(Calendar.HOUR_OF_DAY, 2* partOfTimeHours);
            cal.add(Calendar.MINUTE, 2 * partOfTimeMinutes);
            pairDate.setStartDate(cal.getTime());
            pairDate.setEndDate(endTimeDay);
            return pairDate;
        }
        if (!employee.getPriorityMorning() && !employee.getPriorityAfternoon() && !employee.getPriorityEvening()){
            pairDate.setStartDate(startTimeDay);
            pairDate.setEndDate(endTimeDay);
            return pairDate;
        }
        if (employee.getPriorityMorning() && !employee.getPriorityAfternoon() && employee.getPriorityEvening()){
            Random rand = new Random();
            int randNum = rand.nextInt(1);
            if (randNum == 0){
                //morning
                pairDate.setStartDate(startTimeDay);
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeDay);
                cal.add(Calendar.HOUR_OF_DAY, partOfTimeHours);
                cal.add(Calendar.MINUTE, partOfTimeMinutes);
                pairDate.setEndDate(cal.getTime());
                return pairDate;
            } else {
                //evening
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeDay);
                cal.add(Calendar.HOUR_OF_DAY, 2 * partOfTimeHours);
                cal.add(Calendar.MINUTE, 2 * partOfTimeMinutes);
                pairDate.setStartDate(cal.getTime());
                pairDate.setEndDate(endTimeDay);
                return pairDate;
            }
        }
        return pairDate;
    }

    //Определение, является ли день недели для персонала предпочитаемым выходным
    private boolean checkHolidayPriorityForEmployee(Employee employee, String dayName){
        for (String word : employee.getPriorityHoliday().split(",")){
            if (dayName.equals(word)){
                return false;
            }
        }
        return true;
    }

    private String convertTimeToString(Date date){
        if (date != null){
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            return formatter.format(date);
        } else {
            return null;
        }
    }
}
