package com.myapplication.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.myapplication.activities.ListingActivity;
import com.myapplication.views.SplashView;

public class SplashPresenter {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    private static final int SPLASH_TIME = 1000;

    private Context context;
    private SplashView splashView;

    public SplashPresenter(Context context, SplashView splashView) {
        this.context = context;
        this.splashView = splashView;
    }

    public void launch() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashView.launchListingScreen();
                Intent intent = new Intent(context, ListingActivity.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }, SPLASH_TIME);
    }
}
