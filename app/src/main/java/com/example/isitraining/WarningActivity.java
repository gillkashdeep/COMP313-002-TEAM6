package com.example.isitraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WarningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        final EditText warning = findViewById(R.id.etWarning);

        final Button btnWarning = findViewById(R.id.btnWarningSend);

        btnWarning.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String Warning_Desc = warning.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject();
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                Delivered();
                                Intent intent = new Intent(WarningActivity.this, HomeActivity.class);
                                WarningActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder regFail = new AlertDialog.Builder(WarningActivity.this);
                                regFail.setMessage("Warning Failed to Send")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                WarningRequest warningRequest = new WarningRequest(Warning_Desc, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WarningActivity.this);
                queue.add(warningRequest);
            }
        });

        // set toolbar title
        Toolbar tbWarning = findViewById(R.id.tbWarning);
        setSupportActionBar(tbWarning);
        getSupportActionBar().setTitle("Send Warning");
    }

    void Delivered ()
    {
        Toast.makeText(this, "Warning Sent", Toast.LENGTH_LONG).show();
    }
}
