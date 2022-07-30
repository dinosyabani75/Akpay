package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Button mbtn_login, mbtn_register;
    private TextView btn_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mbtn_login = (Button) findViewById(R.id.button4);
        mbtn_register = (Button) findViewById(R.id.button5);
        btn_admin = (TextView) findViewById(R.id.btn_admin);

        mbtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilogin = new Intent(getApplicationContext(), Login.class);
                startActivity(ilogin);
            }
        });

        mbtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iregis = new Intent(getApplicationContext(), SignUp.class);
                startActivity(iregis);
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adm_log = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(adm_log);
            }
        });
    }
}