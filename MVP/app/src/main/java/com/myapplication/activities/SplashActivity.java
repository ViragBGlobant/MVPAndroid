package com.myapplication.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myapplication.R;
import com.myapplication.utils.LogUtils;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        launchListingScreen();
    }

    private void launchListingScreen() {
        LogUtils.printLog(TAG, "launchListingScreen");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ListingActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
