package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // set toolbar title
        Toolbar tbAccount = findViewById(R.id.tbAccount);
        setSupportActionBar(tbAccount);
        getSupportActionBar().setTitle("Update User Information");
    }

    public void goToFeedbackPage(View view){
        Intent intentFeedback = new Intent(this,FeedbackActivity.class);
        startActivity(intentFeedback);
    }

    // add menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_toobar_logout, menu);

        return true;
    }

    // menu item onclick to different activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.goToHomePageWithoutLogin:
                Intent intentHome = new Intent(this,HomeActivity.class);
                startActivity(intentHome);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
