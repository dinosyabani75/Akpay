package com.proyek.akpay.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyek.akpay.MainActivity;
import com.proyek.akpay.R;
import com.proyek.akpay.Transaksi;

public class Splash_Bayar extends AppCompatActivity {
    private String KEY_NAME = "Nim";
    private final String KEY_kode= "kode";
    private EditText a;
    private Button b, but_salin;

    String nim,kode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__bayar);

        Bundle exstras = getIntent().getExtras();
        nim = exstras.getString(KEY_NAME);
        Bundle exstras2 = getIntent().getExtras();
        kode = exstras2.getString(KEY_kode);
        a = findViewById(R.id.editTextTextPersonName);
        b = findViewById(R.id.button9);
        but_salin = findViewById(R.id.button_copy);
        a.setText(kode);
        a.setFocusable(false);
        a.setEnabled(false);
        a.setCursorVisible(false);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final Handler handler = new Handler();

        but_salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", a.getText().toString().trim());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Splash_Bayar.this, "Kode Berhasil Disalin", Toast.LENGTH_LONG).show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataku = nim;
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                home.putExtra(KEY_NAME,dataku);
                startActivity(home);
            }
        });
    }
}

