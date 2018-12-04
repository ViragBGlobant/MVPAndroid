package com.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myapplication.R;
import com.myapplication.presenters.SplashPresenter;
import com.myapplication.views.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashPresenter splashPresenter = new SplashPresenter(this, this);
        splashPresenter.launch();
    }

    @Override
    public void launchListingScreen() {}
}
