package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class WarningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        // set toolbar title
        Toolbar tbWarning = findViewById(R.id.tbWarning);
        setSupportActionBar(tbWarning);
        getSupportActionBar().setTitle("Send Warning");
    }
}
