package com.example.afgproject;

import android.app.Application;
import android.content.Context;

/**
 * Class used when needed to reference the application in non-activity files
 * Extends application class and serves as name of application in manifest
 */
public class MyApplication extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    /**
     * Get the application instance
     * @return Static instance of application
     */
    public static Application getInstance() {
        return application;
    }

    /**
     * Get the application context
     * @return Static context of application
     */
    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

}