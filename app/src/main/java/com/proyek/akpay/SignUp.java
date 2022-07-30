package com.proyek.akpay;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyek.akpay.Notification.SplashBerhasil;
public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btndaftar, kembali;
    private EditText email, nim, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        mAuth = FirebaseAuth.getInstance();

        btndaftar = (Button) findViewById(R.id.button2);
        kembali = (Button) findViewById(R.id.button3);
        email = (EditText) findViewById(R.id.email);
        nim = (EditText) findViewById(R.id.NIM);
        nama = (EditText) findViewById(R.id.nama);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(back);
            }
        });

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = email.getText().toString().trim();
                String password = nim.getText().toString().trim();
                String name = nama.getText().toString().trim();
                Integer saldo = 0;
                DataItem dataItem = new DataItem(name, password, username, saldo);
                long mDateTime = 9999999999999L - System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);
                //String jurusan = nim.getText().subSequence(0,2).toString();

                if (username.equals("")) {
                    Toast.makeText(SignUp.this, "Silahkan isi email", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(SignUp.this, "Silahkan isi Nim anda", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        myRef.child(password).setValue(dataItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(SignUp.this, "Daftar Berhasil.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Toast.makeText(SignUp.this, "^_^.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent iberhasil = new Intent(getApplicationContext(), SplashBerhasil.class);
                                        startActivity(iberhasil);
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(SignUp.this, "Authentication failed.",
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