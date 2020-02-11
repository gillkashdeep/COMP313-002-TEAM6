package com.example.isitraining;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String Register_Request_URL = "https://isitrain.000webhostapp.com/Register.php";
    private final Map<String, String> params;
    public RegisterRequest(String user_name, String user_mail, String user_password, Response.Listener<String> listener)
    {
        super(Method.POST, Register_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("user_name", user_name);
        params.put("user_mail", user_mail);
        params.put("user_password", user_password);
        System.out.print("database reach!");

    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
