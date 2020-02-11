package com.example.isitraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userName = findViewById(R.id.txtUserName);
        final EditText eMail = findViewById(R.id.txtMail);
        final EditText passWord = findViewById(R.id.txtSetPass);
        final EditText passWordCheck = findViewById(R.id.txtRepeatPass);

        final Button bRegister = findViewById(R.id.btnRegister);

        //Set OnClickListener
        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Set Variable Values
                final String username = userName.getText().toString();
                final String mail = eMail.getText().toString();
                final String password = passWord.getText().toString();
                final String passwordCheck = passWordCheck.getText().toString();
                System.out.print("database reach!");

                //Set up Response Listener
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("any text",response);
                            //Getting Response if adding Data to the Table was Successful
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                //If Registration is not Successful
                                AlertDialog.Builder regFail = new AlertDialog.Builder(RegisterActivity.this);
                                regFail.setMessage("Registration Failed!!!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            //If Response Fails Print Error
                            e.printStackTrace();
                        }
                    }
                };

                //Check If Password Matches Previous Entry
                if (password.matches(passwordCheck))
                {
                    //Sending The Request
                    RegisterRequest registerRequest = new RegisterRequest(username, mail, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
                else
                {
                    //If Password Is Not The Same
                    AlertDialog.Builder passFail = new AlertDialog.Builder(RegisterActivity.this);
                    passFail.setMessage("Passwords Do Not Match")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }

            }
        });

        // set toolbar title
        Toolbar tbRegister = findViewById(R.id.tbRegister);
        setSupportActionBar(tbRegister);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Register");
    }
}
