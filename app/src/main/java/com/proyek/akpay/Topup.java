package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Topup extends AppCompatActivity {
    private String KEY_NAME = "Nim";
    private String nim, saldo;
    private TextInputEditText inp_saldo;
    private TextView getnim, getnama, getemail,getsaldo;
    private Button but_topup;
    private CardView top1, top2, top3;
    private static final String USERS = "message";
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String GetUserID;
    private String cekNIM, cekEmail, cekNama, cekSaldo, ceksaldo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        auth = FirebaseAuth.getInstance();
        //Mendapatkan User ID dari akun yang terautentikasi
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        Bundle exstras = getIntent().getExtras();
        nim = exstras.getString(KEY_NAME);

        inp_saldo = (TextInputEditText)findViewById(R.id.inp_saldo);
        but_topup = (Button)findViewById(R.id.button_topup);
        top1 = (CardView)findViewById(R.id.top_1);
        top2 = (CardView)findViewById(R.id.top_2);
        top3 = (CardView)findViewById(R.id.top_3);
        getnim = (TextView)findViewById(R.id.getnim);
        getnama = (TextView)findViewById(R.id.getnama);
        getemail = (TextView)findViewById(R.id.getemail);
        getsaldo = (TextView)findViewById(R.id.getsaldo);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("nim").getValue().equals(nim)){
                        getnim.setText(ds.child("nim").getValue(String.class).toString().trim());
                        getnama.setText(ds.child("nam").getValue(String.class).toString().trim());
                        getemail.setText(ds.child("email").getValue(String.class).toString().trim());
                        getsaldo.setText(ds.child("saldo").getValue(Integer.class).toString().trim());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        but_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekEmail = getemail.getText().toString().trim();
                cekNama = getnama.getText().toString().trim();
                cekNIM = getnim.getText().toString().trim();
                cekSaldo = getsaldo.getText().toString().trim();
                ceksaldo2 = inp_saldo.getText().toString().trim();
                int q = Integer.parseInt(cekSaldo);
                int p = Integer.parseInt(ceksaldo2);
                int total = p + q;
                long mDateTime = 9999999999999L - System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);
                DataItem setdataitem = new DataItem(cekNama,cekNIM,cekEmail,total);
                userRef.child(cekNIM).setValue(setdataitem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Topup.this, "Berhasil Update.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                String a = nim;
                Intent i = new Intent(getApplicationContext(), Splash_Topup.class);
                i.putExtra(KEY_NAME,a);
                startActivity(i);

            }
        });

        //Proses Update data yang sudah ditentukan


        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tambah Saldo 50.000
                inp_saldo.setText("50000");
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tambah Saldo 500.000
                inp_saldo.setText("500000");
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tambah Saldo 1.000.000
                inp_saldo.setText("1000000");
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Topup);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String dataku = nim;
                switch (item.getItemId()) {
                    case R.id.Home:
                        Intent rumah = new Intent(getApplicationContext(), MainActivity.class);
                        rumah.putExtra(KEY_NAME, dataku);
                        startActivity(rumah);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Topup:
                        return true;
                    case R.id.trans:
                        Intent trans = new Intent(getApplicationContext(), Transaksi.class);
                        trans.putExtra(KEY_NAME, dataku);
                        startActivity(trans);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.account:
                        Intent akun = new Intent(getApplicationContext(), Profile.class);
                        akun.putExtra(KEY_NAME, dataku);
                        startActivity(akun);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

   /* private void updatedata(DataItem setdataitem) {
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.getReference("Admin")
                .child(userID)
                .child("message")
                .child(getKey)
                .setValue(setdataitem)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        getnama.setText("");
                        getnim.setText("");
                        getemail.setText("");
                        Toast.makeText(new Topup(), "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }*/
    }