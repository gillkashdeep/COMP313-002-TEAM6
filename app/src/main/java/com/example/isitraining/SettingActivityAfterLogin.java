package com.example.isitraining;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingActivityAfterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_after_login);

        // set toolbar title
        Toolbar tbSetAfterLogin = findViewById(R.id.tbSetAfterLogin);
        setSupportActionBar(tbSetAfterLogin);
        getSupportActionBar().setTitle("Setting(After Login)");
    }
}
