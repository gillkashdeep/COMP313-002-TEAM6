package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // set toolbar title
        Toolbar tbAccount = findViewById(R.id.tbAccount);
        setSupportActionBar(tbAccount);
        getSupportActionBar().setTitle("Update User Account Information");
    }

    public void goToFeedbackPage(View view){
        Intent intentFeedback = new Intent(this,FeedbackActivity.class);
        startActivity(intentFeedback);
    }
}
