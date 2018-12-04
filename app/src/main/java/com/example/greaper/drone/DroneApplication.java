package com.example.greaper.drone;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class DroneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
