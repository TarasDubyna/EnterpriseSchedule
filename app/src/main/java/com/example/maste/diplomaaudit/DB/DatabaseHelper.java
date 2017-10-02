package com.example.maste.diplomaaudit.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maste.diplomaaudit.Model.Day;
import com.example.maste.diplomaaudit.Model.Department;
import com.example.maste.diplomaaudit.Model.Employee;
import com.example.maste.diplomaaudit.Model.Enterprise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Diploma_Audit.db";

    private static final String TABLE_NAME_ENTERPRISE = "enterprise_table";
    private static final String TABLE_NAME_DEPARTMENT = "department_table";
    private static final String TABLE_NAME_EMPLOYEE = "employee_table";
    private static final String TABLE_NAME_NOT_STARTED_JOB = "not_started_job_table";
    private static final String TABLE_NAME_FINISHED_JOB = "finished_job_table";


    //name Enterprise columns
    private static final String COL_ENTERPRISE_ID = "id";
    private static final String COL_ENTERPRISE_NAME = "name";
    private static final String COL_ENTERPRISE_MON_START = "monday_start_time";
    private static final String COL_ENTERPRISE_MON_END = "monday_end_time";
    private static final String COL_ENTERPRISE_TUE_START = "tuesday_start_time";
    private static final String COL_ENTERPRISE_TUE_END = "tuesday_end_time";
    private static final String COL_ENTERPRISE_WED_START = "wednesday_start_time";
    private static final String COL_ENTERPRISE_WED_END = "wednesday_end_time";
    private static final String COL_ENTERPRISE_THU_START = "thursday_start_time";
    private static final String COL_ENTERPRISE_THU_END = "thursday_end_time";
    private static final String COL_ENTERPRISE_FRI_START = "friday_start_time";
    private static final String COL_ENTERPRISE_FRI_END = "friday_end_time";
    private static final String COL_ENTERPRISE_SAT_START = "saturday_start_time";
    private static final String COL_ENTERPRISE_SAT_END = "saturday_end_time";
    private static final String COL_ENTERPRISE_SUN_START = "sunday_start_time";
    private static final String COL_ENTERPRISE_SUN_END = "sunday_end_time";

    //name Department columns
    private static final String COL_DEPARTMENT_ID = "id";
    private static final String COL_DEPARTMENT_NAME = "name";
    private static final String COL_DEPARTMENT_NUM_EMPLOYEES = "num_employees";
    private static final String COL_DEPARTMENT_ALL_DAY_TIME = "all_day_time";
    private static final String COL_DEPARTMENT_ENTERPRISE_NAME = "enterprise_name";

    //name Employee columns
    private static final String COL_EMPLOYEE_ID = "id";
    private static final String COL_EMPLOYEE_NAME = "name";
    private static final String COL_EMPLOYEE_LAST_NAME = "lastname";
    private static final String COL_EMPLOYEE_SURENAME = "surename";
    private static final String COL_EMPLOYEE_PRIORITY_HOLIDAYS = "priority_holidays";
    private static final String COL_EMPLOYEE_PRIORITY_MORNING = "priority_morning";
    private static final String COL_EMPLOYEE_PRIORITY_AFTERNOON = "priority_afternoon";
    private static final String COL_EMPLOYEE_PRIORITY_EVENING = "priority_evening";
    private static final String COL_EMPLOYEE_MIN_TIME = "min_time";
    private static final String COL_EMPLOYEE_MAX_TIME = "max_time";
    private static final String COL_EMPLOYEE_DEPARTMENT_NAME = "department_name";

    //name NOT_STARTED_JOB columns
    private static final String COL_NOT_STARTED_JOB_ID = "id";
    private static final String COL_NOT_STARTED_JOB_NAME = "name";
    private static final String COL_NOT_STARTED_JOB_MAX_TIME = "max_time_job";
    private static final String COL_NOT_STARTED_JOB_TIME_NOT_EARLY = "time_not_early";
    private static final String COL_NOT_STARTED_JOB_TYPE = "type";

    //name FINISHED_JOB columns
    private static final String COL_FINISHED_JOB_ID = "id";
    private static final String COL_FINISHED_JOB_NAME = "name";
    private static final String COL_FINISHED_JOB_FINISHED_TIME = "finished_time";
    private static final String COL_FINISHED_JOB_MAX_TIME = "max_time_job";
    private static final String COL_FINISHED_JOB_TIME_NOT_EARLY = "time_not_early";
    private static final String COL_FINISHED_JOB_TYPE = "type";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_ENTERPRISE + " (" + COL_ENTERPRISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ENTERPRISE_NAME + " TEXT, "
                + COL_ENTERPRISE_MON_START + " TEXT, " + COL_ENTERPRISE_MON_END + " TEXT, "
                + COL_ENTERPRISE_TUE_START + " TEXT, " + COL_ENTERPRISE_TUE_END + " TEXT, "
                + COL_ENTERPRISE_WED_START + " TEXT, " + COL_ENTERPRISE_WED_END + " TEXT, "
                + COL_ENTERPRISE_THU_START + " TEXT, " + COL_ENTERPRISE_THU_END + " TEXT, "
                + COL_ENTERPRISE_FRI_START + " TEXT, " + COL_ENTERPRISE_FRI_END + " TEXT, "
                + COL_ENTERPRISE_SAT_START + " TEXT, " + COL_ENTERPRISE_SAT_END + " TEXT, "
                + COL_ENTERPRISE_SUN_START + " TEXT, " + COL_ENTERPRISE_SUN_END + " TEXT);");

        db.execSQL("create table " + TABLE_NAME_DEPARTMENT + " (" + COL_DEPARTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DEPARTMENT_NAME + " TEXT, "
                + COL_DEPARTMENT_NUM_EMPLOYEES + " INTEGER, " + COL_DEPARTMENT_ALL_DAY_TIME + " INTEGER, " + COL_DEPARTMENT_ENTERPRISE_NAME + " TEXT);");

        db.execSQL("create table " + TABLE_NAME_EMPLOYEE + " (" + COL_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EMPLOYEE_NAME + " TEXT, " + COL_EMPLOYEE_LAST_NAME + " TEXT, " + COL_EMPLOYEE_SURENAME + " TEXT, "
                + COL_EMPLOYEE_PRIORITY_HOLIDAYS + " TEXT, " + COL_EMPLOYEE_PRIORITY_MORNING + " INTEGER, " + COL_EMPLOYEE_PRIORITY_AFTERNOON + " INTEGER, "
                + COL_EMPLOYEE_PRIORITY_EVENING + " INTEGER, " + COL_EMPLOYEE_MIN_TIME + " INTEGER, " + COL_EMPLOYEE_MAX_TIME + " INTEGER, " + COL_EMPLOYEE_DEPARTMENT_NAME + " TEXT);");

        db.execSQL("create table " + TABLE_NAME_NOT_STARTED_JOB + " (" + COL_NOT_STARTED_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOT_STARTED_JOB_NAME + " TEXT, "
                + COL_NOT_STARTED_JOB_MAX_TIME + " TEXT, " + COL_NOT_STARTED_JOB_TIME_NOT_EARLY + " TEXT, " + COL_NOT_STARTED_JOB_TYPE+ " TEXT);");

        db.execSQL("create table " + TABLE_NAME_FINISHED_JOB + " (" + COL_FINISHED_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_FINISHED_JOB_NAME + " TEXT, "
                + COL_FINISHED_JOB_FINISHED_TIME + " TEXT, " + COL_FINISHED_JOB_MAX_TIME + " TEXT, " + COL_FINISHED_JOB_TIME_NOT_EARLY + " TEXT, " + COL_FINISHED_JOB_TYPE + " TEXT);");

        //createDepartments();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ENTERPRISE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOT_STARTED_JOB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FINISHED_JOB);
        onCreate(db);
    }

    public boolean addEnterpriseDB(Enterprise enterprise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result;
        cv.put(COL_ENTERPRISE_NAME, enterprise.getName());
        cv.put(COL_ENTERPRISE_MON_START, enterprise.getDays().get(0).getStartDayTime());
        cv.put(COL_ENTERPRISE_MON_END, enterprise.getDays().get(0).getEndDayTime());
        cv.put(COL_ENTERPRISE_TUE_START, enterprise.getDays().get(1).getStartDayTime());
        cv.put(COL_ENTERPRISE_TUE_END, enterprise.getDays().get(1).getEndDayTime());
        cv.put(COL_ENTERPRISE_WED_START, enterprise.getDays().get(2).getStartDayTime());
        cv.put(COL_ENTERPRISE_WED_END, enterprise.getDays().get(2).getEndDayTime());
        cv.put(COL_ENTERPRISE_THU_START, enterprise.getDays().get(3).getStartDayTime());
        cv.put(COL_ENTERPRISE_THU_END, enterprise.getDays().get(3).getEndDayTime());
        cv.put(COL_ENTERPRISE_FRI_START, enterprise.getDays().get(4).getStartDayTime());
        cv.put(COL_ENTERPRISE_FRI_END, enterprise.getDays().get(4).getEndDayTime());
        cv.put(COL_ENTERPRISE_SAT_START, enterprise.getDays().get(5).getStartDayTime());
        cv.put(COL_ENTERPRISE_SAT_END, enterprise.getDays().get(5).getEndDayTime());
        cv.put(COL_ENTERPRISE_SUN_START, enterprise.getDays().get(6).getStartDayTime());
        cv.put(COL_ENTERPRISE_SUN_END, enterprise.getDays().get(6).getEndDayTime());
        result = db.insertWithOnConflict(TABLE_NAME_ENTERPRISE, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1) return false;
        else return true;
    }
    public boolean addDepartmentDB(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result;
        cv.put(COL_DEPARTMENT_NAME, department.getName());
        cv.put(COL_DEPARTMENT_NUM_EMPLOYEES, department.getNumEmployees());
        //cv.put(COL_DEPARTMENT_ALL_DAY_TIME, convertBooleanToInteger(department.getAllDayTime()));
        cv.put(COL_DEPARTMENT_ENTERPRISE_NAME, department.getEnterpriseName());
        result = db.insertWithOnConflict(TABLE_NAME_DEPARTMENT, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1) return false;
        else return true;
    }
    public boolean addEmployeeDB(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result;
        cv.put(COL_EMPLOYEE_NAME, employee.getName());
        cv.put(COL_EMPLOYEE_LAST_NAME, employee.getLastName());
        cv.put(COL_EMPLOYEE_SURENAME, employee.getSureName());
        cv.put(COL_EMPLOYEE_PRIORITY_HOLIDAYS, employee.getPriorityHoliday());
        cv.put(COL_EMPLOYEE_PRIORITY_MORNING, convertBooleanToInteger(employee.getPriorityMorning()));
        cv.put(COL_EMPLOYEE_PRIORITY_AFTERNOON, convertBooleanToInteger(employee.getPriorityAfternoon()));
        cv.put(COL_EMPLOYEE_PRIORITY_EVENING, convertBooleanToInteger(employee.getPriorityEvening()));
        cv.put(COL_EMPLOYEE_MIN_TIME, employee.getMinTime());
        cv.put(COL_EMPLOYEE_MAX_TIME, employee.getMaxTime());
        cv.put(COL_EMPLOYEE_DEPARTMENT_NAME, employee.getDepartmentName());
        result = db.insertWithOnConflict(TABLE_NAME_EMPLOYEE, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1) return false;
        else return true;
    }

    public void deleteEnterpriseDB(String enterpriseName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME_ENTERPRISE + " where " + COL_ENTERPRISE_NAME + " = ?;", new String[] {enterpriseName});
    }

    public ArrayList<Enterprise> getAllEnterprises(){
        ArrayList<Enterprise> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT * from " + TABLE_NAME_ENTERPRISE, null);
        if (res.getCount() == 0){
            System.out.println("Enterprise table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    Enterprise enterprise = new Enterprise();
                    //enterprise.setId(res.getInt(0));
                    enterprise.setName(res.getString(1));
                    list.add(enterprise);
                }while (res.moveToNext());
            }
        }
        return list;
    }

    public ArrayList<String> getAllDepartments(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT name from " + TABLE_NAME_DEPARTMENT, null);
        if (res.getCount() == 0){
            System.out.println("Department table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    list.add(res.getString(0));
                } while (res.moveToNext());
            }
        }
        return list;
    }
    public ArrayList<String> getAllDepartments(String enterpriseName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_DEPARTMENT + " where " + COL_DEPARTMENT_ENTERPRISE_NAME + " = ?;", new String[] {enterpriseName});
        ArrayList<String> list = new ArrayList<>();
        if (res.getCount() == 0){
            System.out.println("Enterprise table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    list.add(res.getString(1));
                } while (res.moveToNext());
            }
        }
        return list;
    }

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT name from " + TABLE_NAME_EMPLOYEE, null);
        if (res.getCount() == 0){
            System.out.println("Employee table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    Employee employee = new Employee();
                    employee.setName(res.getString(1));
                    employee.setLastName(res.getString(2));
                    employee.setSureName(res.getString(3));
                } while (res.moveToNext());
            }
        }
        return list;
    }
    public ArrayList<Employee> getAllEmployees(String departmentName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_EMPLOYEE + " where " + COL_EMPLOYEE_DEPARTMENT_NAME + " = ?;", new String[] {departmentName});
        ArrayList<Employee> list = new ArrayList<>();
        if (res.getCount() == 0){
            System.out.println("Employee table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    Employee employee = new Employee();
                    employee.setName(res.getString(1));
                    employee.setLastName(res.getString(2));
                    employee.setSureName(res.getString(3));
                    employee.setPriorityHoliday(res.getString(4));
                    employee.setPriorityMorning(convertIntegerToBoolean(res.getInt(5)));
                    employee.setPriorityAfternoon(convertIntegerToBoolean(res.getInt(6)));
                    employee.setPriorityEvening(convertIntegerToBoolean(res.getInt(7)));
                    employee.setMinTime(res.getInt(8));
                    employee.setMaxTime(res.getInt(9));
                    employee.setDepartmentName(res.getString(10));
                    list.add(employee);
                } while (res.moveToNext());
            }
        }
        return list;
    }

    public int getEnterpriseIDbyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ENTERPRISE + " where name = " + name, null);
        int enterpriseID = 0;
        if (res.getCount() == 0){
            System.out.println("Enterprise table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                enterpriseID = res.getInt(0);
            }
        }
        return enterpriseID;
    }
    public Enterprise getEnterpriseInformation(String enterpriseName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT * from " + TABLE_NAME_ENTERPRISE + " where " + COL_ENTERPRISE_NAME + " = ?;", new String[] {enterpriseName});
        Enterprise enterprise = new Enterprise();
        if (res.getCount() == 0){

        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                enterprise.setName(res.getString(1));
                enterprise.setMondayStartTime(convertStringToDate(res.getString(2)));
                enterprise.setMondayEndTime(convertStringToDate(res.getString(3)));
                enterprise.setTuesdayStartTime(convertStringToDate(res.getString(4)));
                enterprise.setTuesdayEndTime(convertStringToDate(res.getString(5)));
                enterprise.setWednesdayStartTime(convertStringToDate(res.getString(6)));
                enterprise.setWednesdayEndTime(convertStringToDate(res.getString(7)));
                enterprise.setThursdayStartTime(convertStringToDate(res.getString(8)));
                enterprise.setThursdayEndTime(convertStringToDate(res.getString(9)));
                enterprise.setFridayStartTime(convertStringToDate(res.getString(10)));
                enterprise.setFridayEndTime(convertStringToDate(res.getString(11)));
                enterprise.setSaturdayStartTime(convertStringToDate(res.getString(12)));
                enterprise.setSaturdayEndTime(convertStringToDate(res.getString(13)));
                enterprise.setSundayStartTime(convertStringToDate(res.getString(14)));
                enterprise.setSundayEndTime(convertStringToDate(res.getString(15)));
            }
        }
        return enterprise;
    }

    public Department getDepartmentInformation(String departmentName){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT * from " + TABLE_NAME_DEPARTMENT + " where " + COL_DEPARTMENT_NAME + " = ?;", new String[] {departmentName});
        Department department = new Department();
        if (res.getCount() == 0){

        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                department.setName(res.getString(1));
                department.setNumEmployees(res.getInt(2));
                department.setAllDayTime(convertIntegerToBoolean(res.getInt(3)));
                department.setEnterpriseName(res.getString(4));
            }
        }
        return department;
    }

    public ArrayList<Day> getWorkTimeEnterprise(String enterpriseName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ENTERPRISE + " where " + COL_ENTERPRISE_NAME + " = ?;", new String[] {enterpriseName.toString()});
        ArrayList<Day> list = new ArrayList<>();
        if (res.getCount() == 0){

        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                list.add(new Day("Monday",res.getString(2),res.getString(3)));
                list.add(new Day("Tuesday",res.getString(4),res.getString(5)));
                list.add(new Day("Wednesday",res.getString(6),res.getString(7)));
                list.add(new Day("Thursday",res.getString(8),res.getString(9)));
                list.add(new Day("Friday",res.getString(10),res.getString(11)));
                list.add(new Day("Saturday",res.getString(12),res.getString(13)));
                list.add(new Day("Sunday",res.getString(14),res.getString(15)));
            }
        }
        return list;
    }

    private int convertBooleanToInteger(boolean input){
        if (input){
            return 1;
        } else {
            return 0;
        }
    }

    private boolean convertIntegerToBoolean(int bool){
        if (bool == 1){
            return true;
        } else {
            return false;
        }
    }

    private Date convertStringToDate(String stringTime){
        if (!stringTime.equals("--:--")){
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            try {
                return formatter.parse(stringTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
