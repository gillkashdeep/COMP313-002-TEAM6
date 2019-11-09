package com.example.isitraining;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationActivityAfterLogin extends AppCompatActivity {

    private TextView notification;
    private TextView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_after_login);

        notification = findViewById(R.id.weatherUpdateAfterLogin);
//        weather = findViewById(R.id.tvPresentWeatherHome);

        // set toolbar title
        Toolbar tbNotiAfterLogin = findViewById(R.id.tbNotiAfterLogin);
        setSupportActionBar(tbNotiAfterLogin);
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
