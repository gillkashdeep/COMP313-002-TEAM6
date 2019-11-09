package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // set toolbar title
        Toolbar tbAccount = findViewById(R.id.tbAccount);
        setSupportActionBar(tbAccount);
        getSupportActionBar().setTitle("User Account");
    }
}
