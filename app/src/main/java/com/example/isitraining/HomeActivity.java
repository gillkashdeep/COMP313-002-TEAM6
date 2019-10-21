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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    // initiate views that relate to current weather
    TextView tvCityHome, tvTemHome, tvPresentWeatherHome;
    ImageView ivPresentWeatherHome;

    // initiate views that relate to 3 hours forecast
    TextView tvTime0Home, tvTime0WeatherHome, tvTime1Home, tvTime1WeatherHome, tvTime2Home, tvTime2WeatherHome, tvTime3Home, tvTime3WeatherHome;
    ImageView ivTime0WeatherHome, ivTime1WeatherHome, ivTime2WeatherHome, ivTime3WeatherHome;

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

        // find views that relate to 3 hours forecast
        tvTime0Home = findViewById(R.id.tvTime0Home);
        ivTime0WeatherHome = findViewById(R.id.ivTime0WeatherHome);
        tvTime0WeatherHome = findViewById(R.id.tvTime0WeatherHome);

        tvTime1Home = findViewById(R.id.tvTime1Home);
        ivTime1WeatherHome = findViewById(R.id.ivTime1WeatherHome);
        tvTime1WeatherHome = findViewById(R.id.tvTime1WeatherHome);

        tvTime2Home = findViewById(R.id.tvTime2Home);
        ivTime2WeatherHome = findViewById(R.id.ivTime2WeatherHome);
        tvTime2WeatherHome = findViewById(R.id.tvTime2WeatherHome);

        tvTime3Home = findViewById(R.id.tvTime3Home);
        ivTime3WeatherHome = findViewById(R.id.ivTime3WeatherHome);
        tvTime3WeatherHome = findViewById(R.id.tvTime3WeatherHome);
        // get 3 hours forecast method
        get3HoursForecast();

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

                    // set current weather data to views
                    tvCityHome.setText(city);
                    Picasso.get().load(iconURLCurrentWeather).into(ivPresentWeatherHome);
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

    // get 3 hours forecast
    public void get3HoursForecast(){
        // In the future iteration it will be changed to user chosen City
        String toronto3HoursForecastJsonUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Toronto,ca&appid=5dd7fde31d13e47b91a429b41e79b21d&units=metric";

        // establish Json request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, toronto3HoursForecastJsonUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    // get list array from toronto3HoursForecastJson
                    JSONArray listArrayJson = response.getJSONArray("list");

                    // get the first object from listArrayJson
                    JSONObject object0ListArrayJson = listArrayJson.getJSONObject(0);
                    // get main object from the first object
                    JSONObject main0ObjectJson = object0ListArrayJson.getJSONObject("main");
                    // get weather array from the first object
                    JSONArray weather0ArrayJson = object0ListArrayJson.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0Weather0ArrayJson = weather0ArrayJson.getJSONObject(0);
                    // get first weather data
                    String timeWeather0 = (object0ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather0 = "http://openweathermap.org/img/wn/" + object0Weather0ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather0 = (int)Math.floor(main0ObjectJson.getDouble("temp")) + "°C";
                    // set first weather data to views
                    tvTime0Home.setText(timeWeather0);
                    Picasso.get().load(iconURLWeather0).into(ivTime0WeatherHome);
                    tvTime0WeatherHome.setText(tempWeather0);

                    // get the second object from listArrayJson
                    JSONObject object1ListArrayJson = listArrayJson.getJSONObject(1);
                    // get main object from the second object
                    JSONObject main1ObjectJson = object1ListArrayJson.getJSONObject("main");
                    // get weather array from the second object
                    JSONArray weather1ArrayJson = object1ListArrayJson.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0Weather1ArrayJson = weather1ArrayJson.getJSONObject(0);
                    // get second weather data
                    String timeWeather1 = (object1ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather1 = "http://openweathermap.org/img/wn/" + object0Weather1ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather1 = (int)Math.floor(main1ObjectJson.getDouble("temp")) + "°C";
                    // set second weather data to views
                    tvTime1Home.setText(timeWeather1);
                    Picasso.get().load(iconURLWeather1).into(ivTime1WeatherHome);
                    tvTime1WeatherHome.setText(tempWeather1);

                    // get the third object from listArrayJson
                    JSONObject object2ListArrayJson = listArrayJson.getJSONObject(2);
                    // get main object from the second object
                    JSONObject main2ObjectJson = object2ListArrayJson.getJSONObject("main");
                    // get weather array from the second object
                    JSONArray weather2ArrayJson = object2ListArrayJson.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0Weather2ArrayJson = weather2ArrayJson.getJSONObject(0);
                    // get second weather data
                    String timeWeather2 = (object2ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather2 = "http://openweathermap.org/img/wn/" + object0Weather2ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather2 = (int)Math.floor(main2ObjectJson.getDouble("temp")) + "°C";
                    // set second weather data to views
                    tvTime2Home.setText(timeWeather2);
                    Picasso.get().load(iconURLWeather2).into(ivTime2WeatherHome);
                    tvTime2WeatherHome.setText(tempWeather2);


                    // get the fourth object from listArrayJson
                    JSONObject object3ListArrayJson = listArrayJson.getJSONObject(3);
                    // get main object from the second object
                    JSONObject main3ObjectJson = object3ListArrayJson.getJSONObject("main");
                    // get weather array from the second object
                    JSONArray weather3ArrayJson = object3ListArrayJson.getJSONArray("weather");
                    // get the first object from  weather array
                    JSONObject object0Weather3ArrayJson = weather3ArrayJson.getJSONObject(0);
                    // get second weather data
                    String timeWeather3 = (object3ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather3 = "http://openweathermap.org/img/wn/" + object0Weather3ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather3 = (int)Math.floor(main3ObjectJson.getDouble("temp")) + "°C";
                    // set second weather data to views
                    tvTime3Home.setText(timeWeather3);
                    Picasso.get().load(iconURLWeather3).into(ivTime3WeatherHome);
                    tvTime3WeatherHome.setText(tempWeather3);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // add request queue to Volley
        RequestQueue queueCurrentWeather = Volley.newRequestQueue(this);
        queueCurrentWeather.add(jsonObjectRequest);
    }
}
