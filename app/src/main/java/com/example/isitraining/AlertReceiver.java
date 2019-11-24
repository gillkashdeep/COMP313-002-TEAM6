package com.example.isitraining;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.MODE_PRIVATE;
import static com.example.isitraining.NotificationChannelSetup.CHANNEL_1_ID;

public class AlertReceiver extends BroadcastReceiver {

    //declaration of notification  variables
    private NotificationManagerCompat notificationManagerCompat;
    String currentWeatherJsonUrl, user_name;

    @Override
    public void onReceive(final Context context, Intent intent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("prefsAlarm", MODE_PRIVATE);
        currentWeatherJsonUrl = sharedPreferences.getString("currentWeatherJsonUrl", "");
        user_name = sharedPreferences.getString("user_name", "");

        notificationManagerCompat = NotificationManagerCompat.from(context);

        // establish Json request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, currentWeatherJsonUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    // get main object from torontoCurrentWeatherJson
                    JSONObject mainObjectJson = response.getJSONObject("main");
                    // get weather array from torontoCurrentWeatherJson
                    JSONArray weatherArrayJson = response.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0WeatherArrayJson = weatherArrayJson.getJSONObject(0);

                    int temp = (int)Math.floor(mainObjectJson.getDouble("temp"));
                    String currentWeather = object0WeatherArrayJson.getString("main");

                    NotificationUtility notificationUtility = new NotificationUtility();

                    //Send Raining Notification
                    notificationUtility.sendNotification(context, notificationManagerCompat, currentWeather, user_name, temp);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // add request queue to Volley
        RequestQueue queueCurrentWeather = Volley.newRequestQueue(context);
        queueCurrentWeather.add(jsonObjectRequest);
    }
}
