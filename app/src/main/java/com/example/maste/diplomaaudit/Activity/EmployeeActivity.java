package com.example.maste.diplomaaudit.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maste.diplomaaudit.Adapters.EmployeeAdapter;
import com.example.maste.diplomaaudit.DB.DatabaseHelper;
import com.example.maste.diplomaaudit.Model.Day;
import com.example.maste.diplomaaudit.Model.Employee;
import com.example.maste.diplomaaudit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private View createEmployeeLayout;

    private TextView textDepartmentName;

    private EditText enterEmployeeNameET;
    private EditText enterEmployeeSecondNameET;
    private EditText enterEmployeeSurnameET;

    private EditText enterEmployeeMinHourET;
    private EditText enterEmployeeMaxHourET;

    private CheckBox checkBoxEmployeePriorityMorning;
    private CheckBox checkBoxEmployeePriorityAfternoon;
    private CheckBox checkBoxEmployeePriorityEvening;

    private CheckBox checkBoxEmployeeMon;
    private CheckBox checkBoxEmployeeTue;
    private CheckBox checkBoxEmployeeWed;
    private CheckBox checkBoxEmployeeThu;
    private CheckBox checkBoxEmployeeFri;
    private CheckBox checkBoxEmployeeSat;
    private CheckBox checkBoxEmployeeSun;

    private Button addEmployeeBtn;
    private Button addEmployeeObjBtn;
    private Button showScheduleDepartment;
    private Button addJobBtn;

    private LinearLayout buttonLayout;

    private ListView employeeList;

    EmployeeAdapter adapter;

    String departmentName;
    String enterpriseName;

    ArrayList<String> holidayDayList = new ArrayList<String>();
    ArrayList<String> partOfDayList = new ArrayList<>();
    Map<String,CheckBox> dayIDNameMap = new HashMap<>();
    Map<String,CheckBox> partOfDayNameMap = new HashMap<>();
    ArrayList<Employee> employeeArrayList = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        databaseHelper = new DatabaseHelper(this);

        createEmployeeLayout = findViewById(R.id.create_employee_layout);

        textDepartmentName = (TextView) findViewById(R.id.employee_department_name);
        Intent intent = getIntent();
        departmentName = intent.getStringExtra("department_name");
        enterpriseName = intent.getStringExtra("enterprise_name");
        textDepartmentName.setText(departmentName);

        enterEmployeeNameET = (EditText) findViewById(R.id.employee_name_editText);
        enterEmployeeSecondNameET = (EditText) findViewById(R.id.employee_second_name_editText);
        enterEmployeeSurnameET = (EditText) findViewById(R.id.employee_surname_editText);

        enterEmployeeMinHourET = (EditText) findViewById(R.id.employee_min_hour_editText);
        enterEmployeeMaxHourET = (EditText) findViewById(R.id.employee_max_hour_editText);

        checkBoxEmployeePriorityMorning = (CheckBox) findViewById(R.id.employee_checkbox_priority_morning);
        checkBoxEmployeePriorityAfternoon = (CheckBox) findViewById(R.id.employee_checkbox_priority_afternoon);
        checkBoxEmployeePriorityEvening = (CheckBox) findViewById(R.id.employee_checkbox_priority_evening);

        checkBoxEmployeePriorityMorning.setOnCheckedChangeListener(this);
        checkBoxEmployeePriorityAfternoon.setOnCheckedChangeListener(this);
        checkBoxEmployeePriorityEvening.setOnCheckedChangeListener(this);

        checkBoxEmployeeMon = (CheckBox) findViewById(R.id.employee_checkbox_mon);
        checkBoxEmployeeTue = (CheckBox) findViewById(R.id.employee_checkbox_tue);
        checkBoxEmployeeWed = (CheckBox) findViewById(R.id.employee_checkbox_wed);
        checkBoxEmployeeThu = (CheckBox) findViewById(R.id.employee_checkbox_thu);
        checkBoxEmployeeFri = (CheckBox) findViewById(R.id.employee_checkbox_fri);
        checkBoxEmployeeSat = (CheckBox) findViewById(R.id.employee_checkbox_sat);
        checkBoxEmployeeSun = (CheckBox) findViewById(R.id.employee_checkbox_sun);

        partOfDayNameMap.put("Morning", checkBoxEmployeePriorityMorning);
        partOfDayNameMap.put("Afternoon", checkBoxEmployeePriorityAfternoon);
        partOfDayNameMap.put("Evening", checkBoxEmployeePriorityEvening);

        dayIDNameMap.put("Monday", checkBoxEmployeeMon);
        dayIDNameMap.put("Tuesday", checkBoxEmployeeTue);
        dayIDNameMap.put("Wednesday", checkBoxEmployeeWed);
        dayIDNameMap.put("Thursday", checkBoxEmployeeThu);
        dayIDNameMap.put("Friday", checkBoxEmployeeFri);
        dayIDNameMap.put("Saturday", checkBoxEmployeeSat);
        dayIDNameMap.put("Sunday", checkBoxEmployeeSun);

        setUnClickableCheckBoxesDay(databaseHelper, (HashMap<String, CheckBox>) dayIDNameMap);

        checkBoxEmployeeMon.setOnCheckedChangeListener(this);
        checkBoxEmployeeTue.setOnCheckedChangeListener(this);
        checkBoxEmployeeWed.setOnCheckedChangeListener(this);
        checkBoxEmployeeThu.setOnCheckedChangeListener(this);
        checkBoxEmployeeFri.setOnCheckedChangeListener(this);
        checkBoxEmployeeSat.setOnCheckedChangeListener(this);
        checkBoxEmployeeSun.setOnCheckedChangeListener(this);

        addEmployeeBtn = (Button) findViewById(R.id.add_employee);
        addEmployeeObjBtn = (Button) findViewById(R.id.create_employee_object_btn);
        showScheduleDepartment = (Button) findViewById(R.id.show_schedule_department);
        //addJobBtn = (Button) findViewById(R.id.add_job_button);

        buttonLayout = (LinearLayout) findViewById(R.id.button_layout);

        employeeList = (ListView) findViewById(R.id.listViewEmployee);

        employeeArrayList.addAll(databaseHelper.getAllEmployees(intent.getStringExtra("department_name")));

        adapter = new EmployeeAdapter(this, employeeArrayList);
        employeeList.setAdapter(adapter);


        addEmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(View.GONE);
                createEmployeeLayout.setVisibility(View.VISIBLE);
                //addEmployeeBtn.setVisibility(View.GONE);
            }
        });

        addEmployeeObjBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotEmptyEditText()){
                    if (isCorrectMinMaxHour(enterEmployeeMinHourET.getText().toString(), enterEmployeeMaxHourET.getText().toString())){
                        Employee employee = new Employee();
                        employee.setName(enterEmployeeNameET.getText().toString());
                        employee.setLastName(enterEmployeeSecondNameET.getText().toString());
                        employee.setSureName(enterEmployeeSurnameET.getText().toString());
                        employee.setMinTime(Integer.parseInt(enterEmployeeMinHourET.getText().toString()));
                        employee.setMaxTime(Integer.parseInt(enterEmployeeMaxHourET.getText().toString()));
                        employee.setPriorityHoliday(setPriorityHolidaysToString(holidayDayList));
                        employee.setPriorityMorning(checkPartOfDayFromList(partOfDayList, "Morning"));
                        employee.setPriorityAfternoon(checkPartOfDayFromList(partOfDayList, "Afternoon"));
                        employee.setPriorityEvening(checkPartOfDayFromList(partOfDayList, "Evening"));
                        employee.setDepartmentName(textDepartmentName.getText().toString());

                        databaseHelper.addEmployeeDB(employee);
                        employeeArrayList.add(employee);
                        adapter.notifyDataSetChanged();
                    }
                }
                createEmployeeLayout.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.VISIBLE);
                //addEmployeeBtn.setVisibility(View.VISIBLE);
                //showScheduleDepartment.setVisibility(View.VISIBLE);
            }
        });

        showScheduleDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, ScheduleActivity.class);
                intent.putExtra("department_name", departmentName);
                intent.putExtra("enterprise_name", enterpriseName);
                startActivity(intent);
            }
        });

        /*
        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
    }

    private boolean isNotEmptyEditText(){
        String text = "Поле вводу: ";
        ArrayList<String> fieldNameList = new ArrayList<>();
        if (enterEmployeeNameET.getText().toString().isEmpty()){
            fieldNameList.add("ім'я");
        }
        if (enterEmployeeSecondNameET.getText().toString().isEmpty()){
            fieldNameList.add("по батькові");
        }
        if (enterEmployeeSurnameET.getText().toString().isEmpty()){
            fieldNameList.add("прізвища");
        }
        if (enterEmployeeMinHourET.getText().toString().isEmpty()){
            fieldNameList.add("мінімального часу");
        }
        if (enterEmployeeMaxHourET.getText().toString().isEmpty()){
            fieldNameList.add("максимального часу");
        }

        if (!fieldNameList.isEmpty()){
            for (int i = 0; i < fieldNameList.size(); i++){
                if (fieldNameList.get(fieldNameList.size() - 1).equals(fieldNameList.get(i))){
                    text += fieldNameList.get(i) + ";";
                    continue;
                }
                text += fieldNameList.get(i) + ", ";
            }
            Toast.makeText(this, text ,Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isCorrectMinMaxHour(String min, String max){
       if (!min.isEmpty() && !max.isEmpty()){
            int intMin;
            int intMax;
            intMin = Integer.parseInt(min);
            intMax = Integer.parseInt(max);
            if (intMin < 0){
                Toast.makeText(EmployeeActivity.this, "Мінімальне значення годин не може бути від`ємним!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (intMax < 0){
                Toast.makeText(EmployeeActivity.this, "Максимальне значення годин не може бути від`ємним!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (intMin > intMax){
                Toast.makeText(EmployeeActivity.this, "Мінімальне значення годин більше за максимальне", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void setNotCheckedIfHoliday(String enterpriseName){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<Day> timeList = new ArrayList<>();
        timeList.addAll(databaseHelper.getWorkTimeEnterprise(enterpriseName));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        for (HashMap.Entry entry: dayIDNameMap.entrySet()){
            if (entry.getValue().equals(compoundButton)){
                if (checked){
                    if (!holidayDayList.contains(entry.getKey().toString())){
                        holidayDayList.add(entry.getKey().toString());
                        break;
                    }
                } else {
                    holidayDayList.remove(entry.getKey().toString());
                }
            }
        }
        for (HashMap.Entry entry: partOfDayNameMap.entrySet()){
            if (entry.getValue().equals(compoundButton)){
                if (entry.getValue().equals(compoundButton)){
                    if (checked){
                        if (!partOfDayList.contains(entry.getKey().toString())){
                            partOfDayList.add(entry.getKey().toString());
                            break;
                        }
                    } else {
                        partOfDayList.remove(entry.getKey().toString());
                    }
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        if (createEmployeeLayout.getVisibility() == View.VISIBLE){
            createEmployeeLayout.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(EmployeeActivity.this, DepartmentsActivity.class);
            startActivity(intent);
        }
    }

    private void setUnClickableCheckBoxesDay(DatabaseHelper databaseHelper, HashMap<String,CheckBox> dayIDName){
        ArrayList<Day> days = new ArrayList<>();
        days.addAll(databaseHelper.getWorkTimeEnterprise(enterpriseName));

        for (Day day : days){
            if (day.getStartDayTime().equals("--:--")){
                for (HashMap.Entry entry: dayIDName.entrySet()){
                    if (entry.getKey().equals(day.getDayName())){
                        CheckBox view = (CheckBox) entry.getValue();
                        view.setChecked(true);
                        view.setClickable(false);
                        if (!holidayDayList.contains(day.getDayName())){
                            holidayDayList.add(day.getDayName());
                        }
                    }
                }
            }
        }
    }

    private String setPriorityHolidaysToString(ArrayList<String> holidayDayList){
        String text = "";
        for (int i = 0; i < holidayDayList.size(); i++){
            text += holidayDayList.get(i) + ",";
        }
        return text;
    }

    private boolean checkPartOfDayFromList(ArrayList<String> partOfDayList, String partOfDayName){
        for (String partOfDay : partOfDayList){
            if (partOfDay.equals(partOfDayName)){
                return true;
            }
        }
        return false;
    }

}
