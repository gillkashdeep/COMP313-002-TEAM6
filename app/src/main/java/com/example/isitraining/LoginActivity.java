package com.example.isitraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText userName = findViewById(R.id.txtUser);
        final EditText passWord = findViewById(R.id.txtPassword);

        final Button bLogin = findViewById(R.id.btnLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user_name = userName.getText().toString();
                final String user_password = passWord.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                String user_name = jsonResponse.getString("user_name");

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("user_name", user_name);

                                LoginActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder regFail = new AlertDialog.Builder(LoginActivity.this);
                                regFail.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(user_name, user_password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

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
