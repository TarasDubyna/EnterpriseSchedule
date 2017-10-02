package com.example.maste.diplomaaudit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maste.diplomaaudit.DB.DatabaseHelper;
import com.example.maste.diplomaaudit.Model.Enterprise;
import com.example.maste.diplomaaudit.R;


public class CreateEnterpriseActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enterEnterpriseET;

    private Button mondayBtn;
    private Button tuesdayBtn;
    private Button wednesdayBtn;
    private Button thursdayBtn;
    private Button fridayBtn;
    private Button saturdayBtn;
    private Button sundayBtn;

    private Button startBtn;
    private Button endBtn;
    private Button noneTimeBtn;

    private Button addEnterpriseBtn;

    private TimePicker timePicker;

    private TextView startText;
    private TextView endText;

    private String minuteTime;
    private String hourTime;
    private String day;

    private Enterprise enterprise = new Enterprise();

    private String[] daysNames = {"Monday","Tuesday","Wednesday","Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enterprise);

        enterEnterpriseET = (EditText) findViewById(R.id.enter_enterprise_name);

        mondayBtn = (Button) findViewById(R.id.btn_mon);
        tuesdayBtn = (Button) findViewById(R.id.btn_tue);
        wednesdayBtn = (Button) findViewById(R.id.btn_wed);
        thursdayBtn = (Button) findViewById(R.id.btn_thu);
        fridayBtn = (Button) findViewById(R.id.btn_fri);
        saturdayBtn = (Button) findViewById(R.id.btn_sat);
        sundayBtn = (Button) findViewById(R.id.btn_sun);

        startBtn = (Button) findViewById(R.id.btn_start);
        endBtn = (Button) findViewById(R.id.btn_end);
        noneTimeBtn = (Button) findViewById(R.id.none_time_btn);

        addEnterpriseBtn = (Button) findViewById(R.id.create_enterprise_object_btn);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        startText = (TextView) findViewById(R.id.text_start);
        endText = (TextView) findViewById(R.id.text_end);

        //-------------------------------------------------

        //Enterprise enterprise = new Enterprise();

        mondayBtn.setOnClickListener(this);
        tuesdayBtn.setOnClickListener(this);
        wednesdayBtn.setOnClickListener(this);
        thursdayBtn.setOnClickListener(this);
        fridayBtn.setOnClickListener(this);
        saturdayBtn.setOnClickListener(this);
        sundayBtn.setOnClickListener(this);

        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
        noneTimeBtn.setOnClickListener(this);

        addEnterpriseBtn.setOnClickListener(this);


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                hourTime = String.valueOf(hour);
                minuteTime = String.valueOf(minute);
                if (String.valueOf(minute).length() == 1){
                    minuteTime = "0" + minute;
                }
                if (String.valueOf(hour).length() == 1){
                    hourTime = "0" + hour;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view.equals(addEnterpriseBtn)){

            enterprise.setName(String.valueOf(enterEnterpriseET.getText()));
            if(enterprise.getName() == null || enterprise.getName().isEmpty()){
                Toast.makeText(this, "Поле назви підпріємства - пусте!", Toast.LENGTH_SHORT).show();
                enterEnterpriseET.setText("");
            } else {
                for (int i = 0; i < enterprise.getDays().size(); i++){
                    if (enterprise.getDays().get(i).getStartDayTime() == null || enterprise.getDays().get(i).getEndDayTime() == null){
                        Toast.makeText(this, "В деяких полях відсутній час!", Toast.LENGTH_SHORT).show();
                    } else {
                        DatabaseHelper db = new DatabaseHelper(this);
                        db.addEnterpriseDB(enterprise);
                        Intent intent = new Intent(CreateEnterpriseActivity.this, EnterpriseActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        }

        if (view.equals(noneTimeBtn)){
            startText.setText("--:--");
            endText.setText("--:--");
            for (int i = 0 ; i < enterprise.getDays().size(); i++){
                if (enterprise.getDays().get(i).getDayName().equals(day)){
                    enterprise.getDays().get(i).setStartDayTime("--:--");
                    enterprise.getDays().get(i).setEndDayTime("--:--");
                    break;
                }
            }
            Toast.makeText(CreateEnterpriseActivity.this, "Времени нету, выходной", Toast.LENGTH_SHORT).show();
            return;
        }

        if (view.equals(startBtn)){
            if (day != null){
                startText.setText(hourTime + ":" + minuteTime);
                for (int i = 0 ; i < enterprise.getDays().size(); i++){
                    if (enterprise.getDays().get(i).getDayName().equals(day)){
                        enterprise.getDays().get(i).setStartDayTime(hourTime + ":" + minuteTime);
                        break;
                    }
                }
                Toast.makeText(CreateEnterpriseActivity.this, "SetDay:" + day + ";StartTime: " + hourTime + ":" + minuteTime, Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(CreateEnterpriseActivity.this, "Виберіть день" + hourTime + ":" + minuteTime, Toast.LENGTH_SHORT).show();
            }

        }

        if (view.equals(endBtn)){
            if (day != null){
                endText.setText(hourTime + ":" + minuteTime);
                for (int i = 0 ; i < enterprise.getDays().size(); i++){
                    if (enterprise.getDays().get(i).getDayName().equals(day)){
                        enterprise.getDays().get(i).setEndDayTime(hourTime + ":" + minuteTime);
                        break;
                    }
                }
                Toast.makeText(CreateEnterpriseActivity.this, "SetDay:" + day + ";EndTime: " + hourTime + ":" + minuteTime, Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(CreateEnterpriseActivity.this, "Виберіть день" + hourTime + ":" + minuteTime, Toast.LENGTH_SHORT).show();
            }
        }

        for (int i = 0; i < daysNames.length; i++ ){
            if (daysNames[i].equals(((Button) findViewById(view.getId())).getHint())){
                mondayBtn.setAlpha(1);
                tuesdayBtn.setAlpha(1);
                wednesdayBtn.setAlpha(1);
                thursdayBtn.setAlpha(1);
                fridayBtn.setAlpha(1);
                saturdayBtn.setAlpha(1);
                sundayBtn.setAlpha(1);

                view.setAlpha((float) 0.8);
                for (int j = 0 ; j < enterprise.getDays().size(); j++){

                    if (enterprise.getDays().get(j).getDayName().equals(((Button) findViewById(view.getId())).getHint())){
                        day = String.valueOf(((Button) findViewById(view.getId())).getHint());
                        if (enterprise.getDays().get(j).getStartDayTime() == null){
                            startText.setText("--:--");
                        }
                        if (enterprise.getDays().get(j).getStartDayTime() == null){
                            endText.setText("--:--");
                        }
                        startText.setText(enterprise.getDays().get(j).getStartDayTime());
                        endText.setText(enterprise.getDays().get(j).getEndDayTime());

                        break;
                    }
                }
                break;
            }
        }
    }
}
