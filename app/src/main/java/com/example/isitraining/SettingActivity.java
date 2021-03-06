package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    String txtChangeLocation;
    String txtChangeCountry;

    Switch switchNotification;
    String isNotiOn;

    EditText etChangeLocation;
    EditText etChangeCountry;
    Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // set toolbar title
        Toolbar tbSet = findViewById(R.id.tbSet);
        setSupportActionBar(tbSet);
        getSupportActionBar().setTitle("Setting");

        switchNotification = findViewById(R.id.switchNotification);

        etChangeLocation = findViewById(R.id.etChangeLocation);
        etChangeCountry = findViewById(R.id.etChangeCountry);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchNotification.isChecked()){
                    isNotiOn = "t";
                }else {
                    isNotiOn = "f";
                    switchNotification.setChecked(false);
                }

                txtChangeLocation = etChangeLocation.getText().toString();
                txtChangeCountry = etChangeCountry.getText().toString();

                if(!txtChangeLocation.equals("") && !txtChangeCountry.equals("")){
                    String result = isNotiOn + txtChangeLocation + "," + txtChangeCountry;

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result", result);

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }else {
                    Toast.makeText(SettingActivity.this, "Please fill all the blank", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
