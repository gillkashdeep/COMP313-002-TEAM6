package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        // set toolbar title
        Toolbar tbAdminGetFeedback = findViewById(R.id.tbAdminGetFeedback);
        setSupportActionBar(tbAdminGetFeedback);
        getSupportActionBar().setTitle("All Feedback");

        final TextView Feedback = findViewById(R.id.txtViewFeedback);

        try {
            JSONObject jsonResponse = new JSONObject();
            String feedback = jsonResponse.getString("Feedback_Desc");
            String username = jsonResponse.getString("User_name");

                Feedback.setText(String.format("%s  %s /n", username, feedback));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // add menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_toobar_admin_use, menu);

        return true;
    }

    // menu item onclick to different activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.goToHomePageWithoutLoginAdmin:
                Intent intentHome = new Intent(this,HomeActivity.class);
                startActivity(intentHome);
                break;
            case R.id.goToWarning:
                Intent intentWarning = new Intent(this,WarningActivity.class);
                startActivity(intentWarning);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
