package com.example.isitraining;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WarningRequest extends StringRequest {

    private static final String Warning_Request_URL = "https://isitraining.000webhostapp.com/Send_Warnings.php";
    private final Map<String, String> params;

    public WarningRequest(String Warning_Desc, Response.Listener<String> listener)
    {
        super(Method.POST, Warning_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("warning_Desc", Warning_Desc);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

}
