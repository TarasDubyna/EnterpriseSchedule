package com.example.maste.diplomaaudit.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maste.diplomaaudit.DB.DatabaseHelper;
import com.example.maste.diplomaaudit.Model.Department;
import com.example.maste.diplomaaudit.R;

import java.util.ArrayList;

public class DepartmentsActivity extends AppCompatActivity {

    private ListView departmentList;

    String enterpriseName;
    ArrayAdapter<String> adapter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        Intent intent = getIntent();
        enterpriseName = intent.getStringExtra("enterprise_name");

        databaseHelper = new DatabaseHelper(this);

        if (databaseHelper.getAllDepartments(enterpriseName).size() == 0){
            databaseHelper.addDepartmentDB( new Department("Аудитори"));
            databaseHelper.addDepartmentDB( new Department("Юристи"));
            databaseHelper.addDepartmentDB( new Department("Технічний персонал"));
        }

        departmentList = (ListView) findViewById(R.id.listViewDepartment);

        final ArrayList<String> list = new ArrayList<>();
        list.addAll(databaseHelper.getAllDepartments());
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        departmentList.setAdapter(adapter);


        departmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DepartmentsActivity.this, EmployeeActivity.class);
                intent.putExtra("department_name", list.get((int) l));
                intent.putExtra("enterprise_name", enterpriseName);
                startActivity(intent);
            }
        });

    }
}
