package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AdminAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        // set toolbar title
        Toolbar tbAccount = findViewById(R.id.tbAdminAccount);
        setSupportActionBar(tbAccount);
        getSupportActionBar().setTitle("Admin Account");
    }
}
