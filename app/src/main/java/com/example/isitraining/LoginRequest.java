package com.example.isitraining;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String Login_Request_URL = "https://isitraining.000webhostapp.com/Login.php";
    private final Map<String, String> params;

    public LoginRequest(String user_name, String user_password, Response.Listener<String> listener)
    {
        super(Request.Method.POST, Login_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("user_name", user_name);
        params.put("user_password", user_password);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

}
