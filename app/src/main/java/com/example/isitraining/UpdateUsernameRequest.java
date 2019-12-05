package com.example.isitraining;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateUsernameRequest extends StringRequest {

    private static final String Username_Update_Request_URL = "https://isitraining.000webhostapp.com/Update_Username.php";
    private final Map<String, String> params;

    public UpdateUsernameRequest(String User_name, String User_name_new, Response.Listener<String> listener)
    {
        super(Method.POST, Username_Update_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("User_name", User_name);
        params.put("User_name_new", User_name_new);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
