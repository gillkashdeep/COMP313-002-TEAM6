package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    private TextView notification;
    private TextView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification = findViewById(R.id.weatherUpdate);
        weather = findViewById(R.id.tvPresentWeatherHome);

        // set toolbar title
        Toolbar tbNoti = findViewById(R.id.tbNoti);
        setSupportActionBar(tbNoti);
        getSupportActionBar().setTitle("Notification");

//        final String change = weather.getText().toString();
//
//        //update
//        if (change.matches("Raining") || change.matches( "RAINING"))
//        {
//            notification.setText("It is Raining! You Should Bring a Umbrella!");
//        }
//        else
//        {
//            notification.setText("No New Notifications");
//        }
    }
}
