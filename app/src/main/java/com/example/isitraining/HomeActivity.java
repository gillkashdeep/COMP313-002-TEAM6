package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    // initiate views that relate to current weather
    TextView tvCityHome, tvTemHome, tvPresentWeatherHome;
    ImageView ivPresentWeatherHome;

    // initiate views that relate to 3 hours forecast
    TextView tvTime0Home, tvTime0WeatherHome, tvTime1Home, tvTime1WeatherHome, tvTime2Home, tvTime2WeatherHome, tvTime3Home, tvTime3WeatherHome;
    ImageView ivTime0WeatherHome, ivTime1WeatherHome, ivTime2WeatherHome, ivTime3WeatherHome;

    // initiate views that relate to 5 days forecast
    TextView tvDay1Home, tvDay1HighTemHome, tvDay1LowTemHome;
    ImageView ivDay1Weather;

    TextView tvDay2Home, tvDay2HighTemHome, tvDay2LowTemHome;
    ImageView ivDay2Weather;

    TextView tvDay3Home, tvDay3HighTemHome, tvDay3LowTemHome;
    ImageView ivDay3Weather;

    TextView tvDay4Home, tvDay4HighTemHome, tvDay4LowTemHome;
    ImageView ivDay4Weather;

    TextView tvDay5Home, tvDay5HighTemHome, tvDay5LowTemHome;
    ImageView ivDay5Weather;

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

        // find views that relate to 5 days forecast
        tvDay1Home = findViewById(R.id.tvDay1Home);
        ivDay1Weather = findViewById(R.id.ivDay1Weather);
        tvDay1HighTemHome = findViewById(R.id.tvDay1HighTemHome);
        tvDay1LowTemHome = findViewById(R.id.tvDay1LowTemHome);

        tvDay2Home = findViewById(R.id.tvDay2Home);
        ivDay2Weather = findViewById(R.id.ivDay2Weather);
        tvDay2HighTemHome = findViewById(R.id.tvDay2HighTemHome);
        tvDay2LowTemHome = findViewById(R.id.tvDay2LowTemHome);

        tvDay3Home = findViewById(R.id.tvDay3Home);
        ivDay3Weather = findViewById(R.id.ivDay3Weather);
        tvDay3HighTemHome = findViewById(R.id.tvDay3HighTemHome);
        tvDay3LowTemHome = findViewById(R.id.tvDay3LowTemHome);

        tvDay4Home = findViewById(R.id.tvDay4Home);
        ivDay4Weather = findViewById(R.id.ivDay4Weather);
        tvDay4HighTemHome = findViewById(R.id.tvDay4HighTemHome);
        tvDay4LowTemHome = findViewById(R.id.tvDay4LowTemHome);

        tvDay5Home = findViewById(R.id.tvDay5Home);
        ivDay5Weather = findViewById(R.id.ivDay5Weather);
        tvDay5HighTemHome = findViewById(R.id.tvDay5HighTemHome);
        tvDay5LowTemHome = findViewById(R.id.tvDay5LowTemHome);
        // get 5 days forecast method
        get5DaysForecast();

    }

    // add menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    // menu item onclick to different activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.goToNotification:
                Intent intentNoti = new Intent(this,NotificationActivity.class);
                startActivity(intentNoti);
                break;
            case R.id.goToSetting:
                Intent intentSet = new Intent(this,SettingActivity.class);
                startActivity(intentSet);
                break;
            case R.id.goToLogin:
                Intent intentLogin = new Intent(this,LoginActivity.class);
                startActivity(intentLogin);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    // get third weather data
                    String timeWeather2 = (object2ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather2 = "http://openweathermap.org/img/wn/" + object0Weather2ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather2 = (int)Math.floor(main2ObjectJson.getDouble("temp")) + "°C";
                    // set third weather data to views
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
                    // get fourth weather data
                    String timeWeather3 = (object3ListArrayJson.getString("dt_txt")).substring(11, 16);
                    String iconURLWeather3 = "http://openweathermap.org/img/wn/" + object0Weather3ArrayJson.getString("icon") + "@2x.png";
                    String tempWeather3 = (int)Math.floor(main3ObjectJson.getDouble("temp")) + "°C";
                    // set fourth weather data to views
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

    // 5 days forecast method
    public void get5DaysForecast(){
        // initiate calendar and date formats
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");

        // get day1's date
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date day1Date = cal.getTime();
        // make them to string
        final String dateDay1 = dateFormat.format(day1Date);
        final String day1 = dayFormat.format(day1Date);

        // get day2's date
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date day2Date = cal.getTime();
        // make them to string
        final String dateDay2 = dateFormat.format(day2Date);
        final String day2 = dayFormat.format(day2Date);

        // get day3's date
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date day3Date = cal.getTime();
        // make them to string
        final String dateDay3 = dateFormat.format(day3Date);
        final String day3 = dayFormat.format(day3Date);

        // get day4's date
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date day4Date = cal.getTime();
        // make them to string
        final String dateDay4 = dateFormat.format(day4Date);
        final String day4 = dayFormat.format(day4Date);

        // get day5's date
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date day5Date = cal.getTime();
        // make them to string
        final String dateDay5 = dateFormat.format(day5Date);
        final String day5 = dayFormat.format(day5Date);

        // In the future iteration it will be changed to user chosen City
        String toronto5DaysForecastJsonUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Toronto,ca&appid=5dd7fde31d13e47b91a429b41e79b21d&units=metric";

        // establish Json request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, toronto5DaysForecastJsonUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    // get list array from toronto3HoursForecastJson
                    JSONArray listArrayJson = response.getJSONArray("list");

                    // initiate temMin and temMax array lists
                    ArrayList<Double> tempMinArrayDay1 = new ArrayList<>();
                    ArrayList<Double> tempMaxArrayDay1 = new ArrayList<>();

                    ArrayList<Double> tempMinArrayDay2 = new ArrayList<>();
                    ArrayList<Double> tempMaxArrayDay2 = new ArrayList<>();

                    ArrayList<Double> tempMinArrayDay3 = new ArrayList<>();
                    ArrayList<Double> tempMaxArrayDay3 = new ArrayList<>();

                    ArrayList<Double> tempMinArrayDay4 = new ArrayList<>();
                    ArrayList<Double> tempMaxArrayDay4 = new ArrayList<>();

                    ArrayList<Double> tempMinArrayDay5 = new ArrayList<>();
                    ArrayList<Double> tempMaxArrayDay5 = new ArrayList<>();

                    // day1`5 weather icon URL string
                    String iconURLWeatherDay1 = "";
                    String iconURLWeatherDay2 = "";
                    String iconURLWeatherDay3 = "";
                    String iconURLWeatherDay4 = "";
                    String iconURLWeatherDay5 = "";


                    // check every object in the Json list array
                    for(int i = 0; i < listArrayJson.length(); i++){
                        // get the i(th) object from listArrayJson
                        JSONObject objectListArrayJson = listArrayJson.getJSONObject(i);
                        // get main object from the i(th) object
                        JSONObject mainObjectJson = objectListArrayJson.getJSONObject("main");
                        // get weather array from the i(th) object
                        JSONArray weatherArrayJson = objectListArrayJson.getJSONArray("weather");
                        // get the first object from  weather array
                        JSONObject object0WeatherArrayJson = weatherArrayJson.getJSONObject(0);
                        // get the date from the i(th) object
                        String dateObject = (objectListArrayJson.getString("dt_txt")).substring(0, 10);

                        // if date in this object is equal to day1 date, store max and min temp to the array lists
                        if(dateObject.equals(dateDay1)){
                            double tempMin = Double.parseDouble(mainObjectJson.getString("temp_min"));
                            double tempMax = Double.parseDouble(mainObjectJson.getString("temp_max"));
                            tempMinArrayDay1.add(tempMin);
                            tempMaxArrayDay1.add(tempMax);

                            // select the last icon url of day1 objects
                            iconURLWeatherDay1 = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                        }

                        // if date in this object is equal to day2 date, store max and min temp to the array lists
                        if(dateObject.equals(dateDay2)){
                            double tempMin = Double.parseDouble(mainObjectJson.getString("temp_min"));
                            double tempMax = Double.parseDouble(mainObjectJson.getString("temp_max"));
                            tempMinArrayDay2.add(tempMin);
                            tempMaxArrayDay2.add(tempMax);

                            // select the last icon url of day2 objects
                            iconURLWeatherDay2 = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                        }

                        // if date in this object is equal to day3 date, store max and min temp to the array lists
                        if(dateObject.equals(dateDay3)){
                            double tempMin = Double.parseDouble(mainObjectJson.getString("temp_min"));
                            double tempMax = Double.parseDouble(mainObjectJson.getString("temp_max"));
                            tempMinArrayDay3.add(tempMin);
                            tempMaxArrayDay3.add(tempMax);

                            // select the last icon url of day3 objects
                            iconURLWeatherDay3 = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                        }

                        // if date in this object is equal to day4 date, store max and min temp to the array lists
                        if(dateObject.equals(dateDay4)){
                            double tempMin = Double.parseDouble(mainObjectJson.getString("temp_min"));
                            double tempMax = Double.parseDouble(mainObjectJson.getString("temp_max"));
                            tempMinArrayDay4.add(tempMin);
                            tempMaxArrayDay4.add(tempMax);

                            // select the last icon url of day4 objects
                            iconURLWeatherDay4 = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                        }

                        // if date in this object is equal to day5 date, store max and min temp to the array lists
                        if(dateObject.equals(dateDay5)){
                            double tempMin = Double.parseDouble(mainObjectJson.getString("temp_min"));
                            double tempMax = Double.parseDouble(mainObjectJson.getString("temp_max"));
                            tempMinArrayDay5.add(tempMin);
                            tempMaxArrayDay5.add(tempMax);

                            // select the last icon url of day5 objects
                            iconURLWeatherDay5 = "http://openweathermap.org/img/wn/" + object0WeatherArrayJson.getString("icon") + "@2x.png";
                        }
                    }

                    // choose the minTemp and maxTemp in minTemp / maxTemp Arrays
                    int tempMinDay1 = Collections.min(tempMinArrayDay1).intValue();
                    int tempMaxDay1 = Collections.max(tempMaxArrayDay1).intValue();

                    int tempMinDay2 = Collections.min(tempMinArrayDay2).intValue();
                    int tempMaxDay2 = Collections.max(tempMaxArrayDay2).intValue();

                    int tempMinDay3 = Collections.min(tempMinArrayDay3).intValue();
                    int tempMaxDay3 = Collections.max(tempMaxArrayDay3).intValue();

                    int tempMinDay4 = Collections.min(tempMinArrayDay4).intValue();
                    int tempMaxDay4 = Collections.max(tempMaxArrayDay4).intValue();

                    int tempMinDay5 = Collections.min(tempMinArrayDay5).intValue();
                    int tempMaxDay5 = Collections.max(tempMaxArrayDay5).intValue();

                    // set day1 data to views
                    tvDay1Home.setText(day1);
                    Picasso.get().load(iconURLWeatherDay1).into(ivDay1Weather);
                    tvDay1HighTemHome.setText(tempMaxDay1 + "°C");
                    tvDay1LowTemHome.setText(tempMinDay1 + "°C");

                    // set day2 data to views
                    tvDay2Home.setText(day2);
                    Picasso.get().load(iconURLWeatherDay2).into(ivDay2Weather);
                    tvDay2HighTemHome.setText(tempMaxDay2 + "°C");
                    tvDay2LowTemHome.setText(tempMinDay2 + "°C");

                    // set day3 data to views
                    tvDay3Home.setText(day3);
                    Picasso.get().load(iconURLWeatherDay3).into(ivDay3Weather);
                    tvDay3HighTemHome.setText(tempMaxDay3 + "°C");
                    tvDay3LowTemHome.setText(tempMinDay3 + "°C");

                    // set day4 data to views
                    tvDay4Home.setText(day4);
                    Picasso.get().load(iconURLWeatherDay4).into(ivDay4Weather);
                    tvDay4HighTemHome.setText(tempMaxDay4 + "°C");
                    tvDay4LowTemHome.setText(tempMinDay4 + "°C");

                    // set day5 data to views
                    tvDay5Home.setText(day5);
                    Picasso.get().load(iconURLWeatherDay5).into(ivDay5Weather);
                    tvDay5HighTemHome.setText(tempMaxDay5 + "°C");
                    tvDay5LowTemHome.setText(tempMinDay5 + "°C");


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
