package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // set toolbar title
        Toolbar tbRegister = findViewById(R.id.tbRegister);
        setSupportActionBar(tbRegister);
        getSupportActionBar().setTitle("Register");
    }
}
