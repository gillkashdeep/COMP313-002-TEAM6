package com.example.isitraining;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.isitraining.NotificationChannelSetup.CHANNEL_1_ID;
import static com.example.isitraining.NotificationChannelSetup.CHANNEL_2_ID;
import static com.example.isitraining.NotificationChannelSetup.CHANNEL_3_ID;

public class NotificationUtility {

    //declaration of notification  variables
//    public NotificationManagerCompat notificationManagerCompat;

    public void sendNotification(Context context, NotificationManagerCompat notificationManagerCompat, String currentWeather, String user_name, int temp){
        //Send Raining Notification
        if (currentWeather.equals("Rain"))
        {
            sendRainNotification(context, notificationManagerCompat);
        }
        else if (currentWeather.equals("Snow"))
        {
            sendSnowNotification(context, notificationManagerCompat);
        }
        else
        {
            sendAllClearNotification(context, notificationManagerCompat);
        }

        try {
            JSONObject jsonResponse = new JSONObject();
            boolean success = jsonResponse.getBoolean("success");
            String warning = jsonResponse.getString("warning_Desc");

            if(success)
            {
                sendWarning(warning, context, notificationManagerCompat);
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
        }


        if (currentWeather.equals("ThunderStorm") || currentWeather.equals("thunderstorm"))
        {
            sendWarningNotification(currentWeather, context, notificationManagerCompat);
        }
    }

    public void sendRainNotification(Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String rainTitle = "Hey I Think it is Raining!";
        String rainMessage = "It's Raining! You Should Bring a Umbrella!";

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

    public void sendSnowNotification(Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String rainTitle = "Hey I Think it is Snowing!";
        String rainMessage = "It's Snowing! You Should Bring be Careful out There!";

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

    public void sendAllClearNotification(Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String clearTitle = "Hey It Looks Great Outside!";
        String clearMessage = "It All Clear Outside! Go Out and Have Some Fun!";

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_weather_update)
                .setContentTitle(clearTitle)
                .setContentText(clearMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (notificationManagerCompat != null)
        {
            notificationManagerCompat.notify(1, notification);
        }
    }

    public void sendWarning(String warning, Context context, NotificationManagerCompat notificationManagerCompat)
    {
        String warningTitle = "Warning";
        String warningMessage = warning;

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
