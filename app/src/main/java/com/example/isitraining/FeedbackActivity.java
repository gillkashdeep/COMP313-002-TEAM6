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

public class FeedbackActivity extends AppCompatActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText feedback = findViewById(R.id.etFeedback);

        final Button bFeedback = findViewById(R.id.btnFeedbackSend);

        final String User_name = LoginActivity.mMyAppsBundle.getString("1");

        bFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String Feedback_Desc = feedback.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Delivered();
                            JSONObject jsonResponse = new JSONObject();
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                Intent intent = new Intent(FeedbackActivity.this, HomeActivity.class);
                                FeedbackActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder regFail = new AlertDialog.Builder(FeedbackActivity.this);
                                regFail.setMessage("Feedback Failed to Send")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                FeedbackRequest feedbackRequest = new FeedbackRequest(User_name, Feedback_Desc, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FeedbackActivity.this);
                queue.add(feedbackRequest);
            }
        });

        // set toolbar title
        Toolbar tbFeedback = findViewById(R.id.tbFeedback);
        setSupportActionBar(tbFeedback);
        getSupportActionBar().setTitle("Send Your Feedback");
    }*/

    void Delivered ()
    {
        Toast.makeText(this, "Feedback Sent", Toast.LENGTH_LONG).show();
    }
}
