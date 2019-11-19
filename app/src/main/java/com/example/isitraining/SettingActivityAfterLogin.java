package com.example.isitraining;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

public class SettingActivityAfterLogin extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_after_login);

        // set toolbar title
        Toolbar tbSetAfterLogin = findViewById(R.id.tbSetAfterLogin);
        setSupportActionBar(tbSetAfterLogin);
        getSupportActionBar().setTitle("Setting(After Login)");

        Button btnOpenTimePicker = findViewById(R.id.btnOpenTimePicker);

        btnOpenTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show((getSupportFragmentManager()), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        TextView tvNotiTime = findViewById(R.id.tvNotiTime);
        if(minute < 10){
            tvNotiTime.setText("Will Notify You At " + hour + ":0" + minute);
        }else {
            tvNotiTime.setText("Will Notify You At " + hour + ":" + minute);
        }

    }
}
