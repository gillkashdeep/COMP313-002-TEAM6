package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set toolbar title
        Toolbar tbLogin = findViewById(R.id.tbLogin);
        setSupportActionBar(tbLogin);
        getSupportActionBar().setTitle("Login");
    }

    public void goToRegisterPage(View view){
        Intent intentRegister = new Intent(this,RegisterActivity.class);
        startActivity(intentRegister);
    }
}
