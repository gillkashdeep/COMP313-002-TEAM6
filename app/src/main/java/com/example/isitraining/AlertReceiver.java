package com.example.isitraining;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Debug;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;

import java.io.Console;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.isitraining.NotificationChannelSetup.CHANNEL_1_ID;

public class AlertReceiver extends BroadcastReceiver {

    //declaration of notification  variables
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onReceive(Context context, Intent intent) {

//        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.start();

        String rainTitle = "Hey I Think it is Raining!";
        String rainMessage = "It's Raining! You Should Bring a Umbrella!";

        notificationManagerCompat = NotificationManagerCompat.from(context);

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
}
