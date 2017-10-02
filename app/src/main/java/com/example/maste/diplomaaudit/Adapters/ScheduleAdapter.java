package com.example.maste.diplomaaudit.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.example.maste.diplomaaudit.R;
import com.example.maste.diplomaaudit.Schedule.PairKeyValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ScheduleAdapter extends BaseExpandableListAdapter{

    private ArrayList<PairKeyValue> monthScheduleList;
    private Context context;

    public ScheduleAdapter(ArrayList<PairKeyValue> monthScheduleList, Context context) {
        this.monthScheduleList = monthScheduleList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return monthScheduleList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return monthScheduleList.get(i).getValue().getWorkShiftList().size();
    }

    @Override
    public Object getGroup(int i) {
        return monthScheduleList.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView dateTextView = (TextView) convertView.findViewById(R.id.list_group_num_day);
        TextView nameDayTextView = (TextView) convertView.findViewById(R.id.list_group_name_day);

        if ((String.valueOf(monthScheduleList.get(groupPosition).getKey().getDayNum())).length() == 1){
            dateTextView.setText("0" + String.valueOf(monthScheduleList.get(groupPosition).getKey().getDayNum()));
        } else {
            dateTextView.setText(String.valueOf(monthScheduleList.get(groupPosition).getKey().getDayNum()));
        }

        if (monthScheduleList.get(groupPosition).getValue().getHoliday()){
            TextView holidayTextView = (TextView) convertView.findViewById(R.id.list_group_holiday);
            holidayTextView.setText(R.string.holiday);
            nameDayTextView.setText(convertWordByLanguage(monthScheduleList.get(groupPosition).getKey().getDayName()));
        }else {
            nameDayTextView.setText(convertWordByLanguage(monthScheduleList.get(groupPosition).getKey().getDayName()));
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.list_item_name);
        TextView lastnameTextView = (TextView) convertView.findViewById(R.id.list_item_lastname);
        TextView surenameTextView = (TextView) convertView.findViewById(R.id.list_item_surename);
        TextView startTimeTextView = (TextView) convertView.findViewById(R.id.list_item_start_time);
        TextView endTimeTextView = (TextView) convertView.findViewById(R.id.list_item_end_time);

        if (monthScheduleList.get(groupPosition).getValue().getWorkShiftList().size() != 0){
            nameTextView.setText(monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition).getEmployee().getName());
            lastnameTextView.setText(monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition).getEmployee().getLastName());
            surenameTextView.setText(monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition).getEmployee().getSureName());
            startTimeTextView.setText(convertTimeToString(monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition).getStartWorkShiftTime()));
            endTimeTextView.setText(convertTimeToString(monthScheduleList.get(groupPosition).getValue().getWorkShiftList().get(childPosition).getEndWorkShiftTime()));
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private String convertTimeToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    private int convertWordByLanguage(String inputName){
        HashMap<String, Integer> languageMap = new HashMap<>();
        languageMap.put("Monday", R.string.monday);
        languageMap.put("Tuesday", R.string.tuesday);
        languageMap.put("Wednesday", R.string.wednesday);
        languageMap.put("Thursday", R.string.thursday);
        languageMap.put("Friday", R.string.friday);
        languageMap.put("Saturday", R.string.saturday);
        languageMap.put("Sunday", R.string.sunday);

        languageMap.put("January", R.string.january);
        languageMap.put("February", R.string.february);
        languageMap.put("March", R.string.march);
        languageMap.put("April", R.string.april);
        languageMap.put("May", R.string.may);
        languageMap.put("June", R.string.june);
        languageMap.put("July", R.string.july);
        languageMap.put("August", R.string.august);
        languageMap.put("September", R.string.september);
        languageMap.put("October", R.string.october);
        languageMap.put("November", R.string.november);
        languageMap.put("December", R.string.december);


        for (HashMap.Entry entry: languageMap.entrySet()){
            if (entry.getKey().equals(inputName)){
                return (int) entry.getValue();
            }
        }
        return 0;
    }

}
