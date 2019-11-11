package com.example.isitraining;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannelSetup extends Application {

    //Name of the Channel ID
    public static final String CHANNEL_1_ID = "notifyWeatherActivity";
    public static final String CHANNEL_2_ID = "notifyClothingActivity";

    @Override
    public void onCreate() {
        super.onCreate();

        //Create Channel Class
        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        //Check if Version is Oreo or Higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            //Setup the Channel
             NotificationChannel weatherChannel = new NotificationChannel(
                            CHANNEL_1_ID,
                            "Weather check",
                            NotificationManager.IMPORTANCE_HIGH
             );
            weatherChannel.setDescription("This is Weather Notifications");

            NotificationChannel clothingChannel = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Clothes report",
                    NotificationManager.IMPORTANCE_HIGH
            );
            weatherChannel.setDescription("This is Clothing Notifications");

             //Creating the Channel
             NotificationManager manager = getSystemService(NotificationManager.class);
             if (manager != null)
             {
                 manager.createNotificationChannel(weatherChannel);
                 manager.createNotificationChannel(clothingChannel);
             }
        }
    }
}
