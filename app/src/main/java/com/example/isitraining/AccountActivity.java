package com.example.isitraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        final EditText user_name_new = findViewById(R.id.etUpdateUser);
        final EditText user_mail_old = findViewById(R.id.etOldMail);
        final EditText user_mail_new = findViewById(R.id.etUpdateMail);
        final EditText password_new = findViewById(R.id.etSetPassUpdate);
        final EditText password_new_check = findViewById(R.id.etRepeatPassUpdate);
        final Button update = findViewById(R.id.btnUpdateReg);

        final String User_name = LoginActivity.mMyAppsBundle.getString("1");
        final String User_password = LoginActivity.mMyAppsBundle.getString("2");

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String User_name_new = user_name_new.getText().toString();
                final String User_mail_old = user_mail_old.getText().toString();
                final String User_mail_new = user_mail_new.getText().toString();
                final String Password_new = password_new.getText().toString();
                final String Password_new_check = password_new_check.getText().toString();

                if (User_name_new != null || User_name_new !="" || User_name_new !=" ")
                {
                    Response.Listener<String> responseListener = new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject();
                                boolean success = jsonResponse.getBoolean("success");

                                if(success)
                                {
                                    String user_field = "Username Updated";
                                    Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                                    AccountActivity.this.startActivity(intent);
                                    Completed(user_field);
                                }
                                else
                                {
                                    AlertDialog.Builder regFail = new AlertDialog.Builder(AccountActivity.this);
                                    regFail.setMessage("Failed to Update Username")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    UpdateUsernameRequest updateUsernameRequest = new UpdateUsernameRequest(User_name, User_name_new, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AccountActivity.this);
                    queue.add(updateUsernameRequest);
                }

                if (User_mail_old == null || User_mail_old == "" || User_mail_old == " ")
                {
                    AlertDialog.Builder regFail = new AlertDialog.Builder(AccountActivity.this);
                    regFail.setMessage("Your old email address is needed if you want to update it.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                if (User_mail_new != null || User_mail_new !="" || User_mail_new !=" ")
                {
                    Response.Listener<String> responseListener = new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject();
                                boolean success = jsonResponse.getBoolean("success");

                                if(success)
                                {
                                    String user_field = "Email Updated";
                                    Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                                    AccountActivity.this.startActivity(intent);
                                    Completed(user_field);
                                }
                                else
                                {
                                    AlertDialog.Builder regFail = new AlertDialog.Builder(AccountActivity.this);
                                    regFail.setMessage("Failed to Update Email")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    UpdateEmailRequest updateEmailRequest = new UpdateEmailRequest(User_mail_old, User_mail_new, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AccountActivity.this);
                    queue.add(updateEmailRequest);
                }

                if (Password_new != null || Password_new !="" || Password_new !=" ")
                {
                    if (Password_new.equals(Password_new_check))
                    {
                        Response.Listener<String> responseListener = new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject();
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success)
                                    {
                                        String user_field = "Password Updated";
                                        Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                                        AccountActivity.this.startActivity(intent);
                                        Completed(user_field);
                                    }
                                    else
                                    {
                                        AlertDialog.Builder regFail = new AlertDialog.Builder(AccountActivity.this);
                                        regFail.setMessage("Failed to Update Password")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };

                        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(User_password, Password_new, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(AccountActivity.this);
                        queue.add(updatePasswordRequest);
                    }
                }
            }
        });

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

    void Completed (String updatedItem)
    {
        Toast.makeText(this, updatedItem, Toast.LENGTH_LONG).show();
    }
}
