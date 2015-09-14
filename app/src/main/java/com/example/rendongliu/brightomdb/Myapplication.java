package com.example.rendongliu.brightomdb;

import android.app.Application;
import android.util.Log;

/**
 * Created by rendong.liu on 25/08/15.
 */
public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(getPackageName(), "start the application");
    }
}

