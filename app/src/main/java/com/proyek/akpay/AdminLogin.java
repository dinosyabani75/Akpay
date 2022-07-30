package com.proyek.akpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminLogin extends AppCompatActivity {
    private TextView btn_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        btn_user = (TextView)findViewById(R.id.btn_user);

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user_log = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(user_log);
            }
        });

    }
}