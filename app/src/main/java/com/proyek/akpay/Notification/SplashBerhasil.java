package com.proyek.akpay.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.proyek.akpay.MainActivity;
import com.proyek.akpay.R;
import com.proyek.akpay.WelcomeActivity;

public class SplashBerhasil extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_berhasil);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                finish();
            }
        }, 3000L); //3000 L = 3 Detik
    }
}