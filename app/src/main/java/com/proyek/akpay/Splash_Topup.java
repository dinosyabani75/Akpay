package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Splash_Topup extends AppCompatActivity {
    private String KEY_NAME = "Nim";
    String nim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__topup);
        Bundle exstras = getIntent().getExtras();
        nim = exstras.getString(KEY_NAME);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            String dataku = nim;
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            home.putExtra(KEY_NAME,dataku);
            startActivity(home);
        }, 3000L); //3000 L = 3 Detik
    }
}
