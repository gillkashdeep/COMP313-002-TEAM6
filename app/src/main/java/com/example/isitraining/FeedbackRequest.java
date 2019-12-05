package com.example.isitraining;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedbackRequest extends StringRequest {

    private static final String Feedback_Request_URL = "https://isitraining.000webhostapp.com/Send_Feedback.php";
    private final Map<String, String> params;

    public FeedbackRequest(String User_name, String Feedback_Desc, Response.Listener<String> listener)
    {
        super(Method.POST, Feedback_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("User_name", User_name);
        params.put("Feedback_Desc", Feedback_Desc);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

}
