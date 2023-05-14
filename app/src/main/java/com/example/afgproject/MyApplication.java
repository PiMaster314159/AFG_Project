package com.example.afgproject;

import android.app.Application;
import android.content.Context;

public final class MyApplication extends Application {
    private static MyApplication instance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext(){
        return MyApplication.context;
    }
}
