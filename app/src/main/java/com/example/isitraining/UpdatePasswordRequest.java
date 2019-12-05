package com.example.isitraining;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordRequest  extends StringRequest {

    private static final String Password_Update_Request_URL = "https://isitraining.000webhostapp.com/Update_Password.php";
    private final Map<String, String> params;

    public UpdatePasswordRequest(String User_password, String User_password_new, Response.Listener<String> listener)
    {
        super(Method.POST, Password_Update_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("User_password", User_password);
        params.put("User_password_new", User_password_new);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
