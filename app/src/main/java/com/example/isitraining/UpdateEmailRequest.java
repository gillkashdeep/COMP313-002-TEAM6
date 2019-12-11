package com.example.isitraining;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateEmailRequest extends StringRequest {

    private static final String Email_Update_Request_URL = "https://isitraining.000webhostapp.com/Update_Email.php";
    private final Map<String, String> params;

    public UpdateEmailRequest(String User_mail_new, String user_name , Response.Listener<String> listener)
    {
        super(Request.Method.POST, Email_Update_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("User_mail_new", User_mail_new);
        params.put("User_name", user_name);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
