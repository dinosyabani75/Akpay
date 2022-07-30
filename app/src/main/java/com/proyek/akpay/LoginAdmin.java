package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginAdmin extends AppCompatActivity {
    private TextView btn_user;
    private TextInputEditText nik;
    private ImageView showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        nik = (TextInputEditText) findViewById(R.id.nik);
        showpass = (ImageView) findViewById(R.id.show_pass_btn);
        btn_user = (TextView) findViewById(R.id.btn_user);

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showpass.getId() == R.id.show_pass_btn) {
                    if (nik.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_visibility_off_24);
                        //Show Password
                        nik.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                        //Hide Password
                        nik.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user_log = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(user_log);
            }
        });
    }
}