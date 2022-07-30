package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button bntlogin, kembali;
    private EditText user, pass;
    private String KEY_NAME = "Nim";
    private ImageView showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bntlogin = (Button) findViewById(R.id.button);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.nim);
        showpass = (ImageView) findViewById(R.id.show_pass_btn);
        mAuth = FirebaseAuth.getInstance();

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showpass.getId() == R.id.show_pass_btn) {
                    if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_visibility_off_24);
                        //Show Password
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                        //Hide Password
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });

        bntlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(Login.this, "Silahkan Input Email Mu", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(Login.this, "Silahkan Input Password Mu", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(username, password).
                            addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                                        menu.putExtra(KEY_NAME, password);
                                        startActivity(menu);
                                        Toast.makeText(Login.this, "Berhasil Masuk",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Login.this, "Gagal Masuk",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    // ...
                                }
                            });
                }

            }
        });
    }
}