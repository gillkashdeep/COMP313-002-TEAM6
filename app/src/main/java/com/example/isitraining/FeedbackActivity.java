package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // set toolbar title
        Toolbar tbFeedback = findViewById(R.id.tbFeedback);
        setSupportActionBar(tbFeedback);
        getSupportActionBar().setTitle("Send Your Feedback");
    }
}
