package com.suwarso.tri.githubuser.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.suwarso.tri.githubuser.R;
import com.suwarso.tri.githubuser.dashboard.DashboardActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}