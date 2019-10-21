package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    // initiate views that relate to current weather
    TextView tvCityHome, tvTemHome, tvPresentWeatherHome;
    ImageView ivPresentWeatherHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set toolbar title
        Toolbar tbHome = findViewById(R.id.tbHome);
        setSupportActionBar(tbHome);
        getSupportActionBar().setTitle("Local Weather");

        // get Date and put date to tvDateHome
        TextView tvDateHome = findViewById(R.id.tvDateHome);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd | EEE");
        tvDateHome.setText(dateFormat.format(cal.getTime()));

        // find views that relate to current weather
        tvCityHome = findViewById(R.id.tvCityHome);
        ivPresentWeatherHome = findViewById(R.id.ivPresentWeatherHome);
        tvTemHome = findViewById(R.id.tvTemHome);
        tvPresentWeatherHome = findViewById(R.id.tvPresentWeatherHome);
        // get current weather method
        getCurrentWeather();

    }

    // add menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    // get current weather
    public void getCurrentWeather(){
        // In the future iteration it will be changed to user chosen City
        String torontoCurrentWeatherJsonUrl = "http://api.openweathermap.org/data/2.5/weather?q=Toronto,ca&appid=5dd7fde31d13e47b91a429b41e79b21d&units=metric";

        // establish Json request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, torontoCurrentWeatherJsonUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    // get main object from torontoCurrentWeatherJson
                    JSONObject mainObjectJson = response.getJSONObject("main");
                    // get weather array from torontoCurrentWeatherJson
                    JSONArray weatherArrayJson = response.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0WeatherArrayJson = weatherArrayJson.getJSONObject(0);

                    // get current weather data
                    String city = response.getString("name");
                    String iconURLCurrentWeather = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                    String tempCurrentWeather = (int)Math.floor(mainObjectJson.getDouble("temp")) + "°C";
                    String currentWeather = object0WeatherArrayJson.getString("main");

                    tvCityHome.setText(city);
                    tvTemHome.setText(tempCurrentWeather);
                    tvPresentWeatherHome.setText(currentWeather);

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
        RequestQueue queueCurrentWeather = Volley.newRequestQueue(this);
        queueCurrentWeather.add(jsonObjectRequest);

    }
}
