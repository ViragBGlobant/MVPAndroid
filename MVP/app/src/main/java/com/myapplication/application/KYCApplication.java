package com.myapplication.application;

import android.app.Application;
import android.content.Context;

public class KYCApplication extends Application {

    private static final String TAG = KYCApplication.class.getSimpleName();

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        KYCApplication.applicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return KYCApplication.applicationContext;
    }
}
