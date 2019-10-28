package com.example.isitraining;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannelSetup extends Application {

    //Name of the Channel ID
    public static final String CHANNEL_1_ID = "notifyRainingActivity";

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
             NotificationChannel rainChannel = new NotificationChannel(
                            CHANNEL_1_ID,
                            "Rain check",
                            NotificationManager.IMPORTANCE_HIGH
             );
             rainChannel.setDescription("This is Raining Notifications");

             //Creating the Channel
             NotificationManager manager = getSystemService(NotificationManager.class);
             if (manager != null)
             {
                 manager.createNotificationChannel(rainChannel);
             }
        }
    }
}
