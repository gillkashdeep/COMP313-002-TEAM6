package com.example.isitraining;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

public class SettingActivityAfterLogin extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    String txtChangeLocationAfterLogin;
    String txtChangeCountryAfterLogin;

    Switch switchNotificationAfterLogin;
    String isNotiOn;

    EditText etChangeLocationAfterLogin;
    EditText etChangeCountryAfterLogin;
    Button btnSaveChangesAfterLogin;

    String hourMinute = "";

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

        switchNotificationAfterLogin = findViewById(R.id.switchNotificationAfterLogin);

        etChangeLocationAfterLogin = findViewById(R.id.etChangeLocationAfterLogin);
        etChangeCountryAfterLogin = findViewById(R.id.etChangeCountryAfterLogin);
        btnSaveChangesAfterLogin = findViewById(R.id.btnSaveChangesAfterLogin);

        btnSaveChangesAfterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchNotificationAfterLogin.isChecked()){
                    isNotiOn = "t";
                }else {
                    isNotiOn = "f";
                    switchNotificationAfterLogin.setChecked(false);
                }

                txtChangeLocationAfterLogin = etChangeLocationAfterLogin.getText().toString();
                txtChangeCountryAfterLogin = etChangeCountryAfterLogin.getText().toString();

                if(!txtChangeLocationAfterLogin.equals("") && !txtChangeCountryAfterLogin.equals("")){
                    String result = isNotiOn + hourMinute + txtChangeLocationAfterLogin + "," + txtChangeCountryAfterLogin;

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result2", result);

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }else {
                    Toast.makeText(SettingActivityAfterLogin.this, "Please fill all the blank", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        String sHour, sMinute;

        if(hour < 10){
            sHour = "0" + hour;
            if(minute < 10){
                sMinute = "0" + minute;
            }else {
                sMinute = Integer.toString(minute);
            }
        }else {
            sHour = Integer.toString(hour);
            if(minute < 10){
                sMinute = "0" + minute;
            }else {
                sMinute = Integer.toString(minute);
            }
        }

        hourMinute = sHour + sMinute;

        TextView tvNotiTime = findViewById(R.id.tvNotiTime);
        if(minute < 10){
            tvNotiTime.setText("Will Notify You At " + hour + ":0" + minute);
        }else {
            tvNotiTime.setText("Will Notify You At " + hour + ":" + minute);
        }

    }
}
