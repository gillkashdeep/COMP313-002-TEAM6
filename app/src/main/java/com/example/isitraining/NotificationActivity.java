package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // set toolbar title
        Toolbar tbNoti = findViewById(R.id.tbNoti);
        setSupportActionBar(tbNoti);
        getSupportActionBar().setTitle("Notification");
    }
}
