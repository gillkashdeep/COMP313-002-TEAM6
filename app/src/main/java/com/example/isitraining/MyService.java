package com.example.isitraining;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public void onCreate() {


    }

    @Override
    public void onStart(Intent intent, int startId) {
        //do something


    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
