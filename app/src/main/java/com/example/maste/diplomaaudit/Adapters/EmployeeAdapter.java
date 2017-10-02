package com.example.maste.diplomaaudit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maste.diplomaaudit.Model.Employee;
import com.example.maste.diplomaaudit.R;

import java.util.ArrayList;


public class EmployeeAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Employee> employees;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.employee_list_items, parent, false);
        }

        Employee employee = getEmployee(position);

        ((TextView) view.findViewById(R.id.employee_list_item_name)).setText(employee.getName());
        ((TextView) view.findViewById(R.id.employee_list_item_lastname)).setText(employee.getLastName());
        ((TextView) view.findViewById(R.id.employee_list_item_surename)).setText(employee.getSureName());

        return view;
    }

    Employee getEmployee(int position){
        return (Employee) getItem(position);
    }
}
