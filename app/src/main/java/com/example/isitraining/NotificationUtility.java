package com.example.isitraining;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.example.isitraining.NotificationChannelSetup.CHANNEL_1_ID;
import static com.example.isitraining.NotificationChannelSetup.CHANNEL_2_ID;
import static com.example.isitraining.NotificationChannelSetup.CHANNEL_3_ID;

public class NotificationUtility extends AppCompatActivity {

    private static final String Warning_Request_URL = "https://isitraining.000webhostapp.com/Retrive_Warnings.php";
    InputStream is;
    String line;
    String result;

    //declaration of notification  variables
//    public NotificationManagerCompat notificationManagerCompat;

    public void sendNotification(Context context, NotificationManagerCompat notificationManagerCompat, String currentWeather, String user_name, int temp, String city){
        //Send Raining Notification
        if (currentWeather.equals("Rain"))
        {
            sendRainNotification(context, notificationManagerCompat,temp,city);
        }
        else if (currentWeather.equals("Snow"))
        {
            sendSnowNotification(context, notificationManagerCompat,temp,city);
        }
        else
        {
            sendAllClearNotification(context, notificationManagerCompat,temp,city);
        }


        //Send Clothing Notification
        if(user_name != null){
            String hc;
            if (temp <= 0)
            {
                hc = "Cold";
                sendClothingNotification(hc, temp, context, notificationManagerCompat);
            }
            else if (temp >= 15)
            {
                hc = "Hot";
                sendClothingNotification(hc, temp, context, notificationManagerCompat);
            }

            StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

            sendWarning(context, notificationManagerCompat);
        }


        if (currentWeather.equals("ThunderStorm") || currentWeather.equals("thunderstorm"))
        {
            sendWarningNotification(currentWeather, context, notificationManagerCompat);
        }
    }

    public void sendRainNotification(Context context, NotificationManagerCompat notificationManagerCompat,int temp, String city)
    {
        String rainTitle = "Hey I Think it is Raining!";
        String rainMessage = "It's Raining! You Should Bring a Umbrella!"+"\n Temp:"+temp+"City:"+city;

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(rainTitle)
                .setContentText(rainMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(1, notification);
        }
    }

    public void sendSnowNotification(Context context, NotificationManagerCompat notificationManagerCompat,int temp, String city)
    {
        String rainTitle = "Hey I Think it is Snowing!";
        String rainMessage = "It's Snowing! You Should Bring be Careful out There!"+"\n Temp:"+temp+"\n City"+city;

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(rainTitle)
                .setContentText(rainMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(1, notification);
        }
    }

    public void sendWarningNotification(String warning, Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String rainTitle = "Hey I Think it is Getting Crazy out There!";
        String rainMessage = "It's " + warning + "! You Should Be Careful Today!";

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(rainTitle)
                .setContentText(rainMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(1, notification);
        }
    }

    public void sendClothingNotification(String hc, int temp, Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String rainTitle = "Hey I Think it is pretty " + hc + " Outside!";
        if (hc.equals("Cold"))
        {
            hc = "Warmly";
        }
        else if (hc.equals("Hot"))
        {
            hc = "Cool";
        }
        String rainMessage = "It's " + temp + "°C. You Should Dress " + hc + " Today.";

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(rainTitle)
                .setContentText(rainMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(2, notification);
        }
    }

    public void sendAllClearNotification(Context context, NotificationManagerCompat notificationManagerCompat, int temp,String  city)
    {
//        Intent intent = getIntent();
//        String temp = intent.getStringExtra("temp");
//        String city = intent.getStringExtra("city");



        String clearTitle = "Hey It Looks Great Outside!";
        //String clearMessage = "It All Clear Outside! Go Out and Have Some Fun!";
        String clearMessage = "It All Clear Outside! Go Out and Have Some Fun!" +"\n Temp:"+temp+",City:"+city;


        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(clearTitle)
                .setContentText(clearMessage)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(clearMessage))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(1, notification);
        }
    }

    public void sendWarning(Context context, NotificationManagerCompat notificationManagerCompat)
    {
        try
        {
            URL url = new URL(Warning_Request_URL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while((line = br.readLine()) != null )
            {
                sb.append(line + "\n");
            }

            result = sb.toString();

            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String warningTitle = "WARNING!!!!";
        String warningMessage = result;

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(warningTitle)
                .setContentText(warningMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(3, notification);
        }
    }
}
