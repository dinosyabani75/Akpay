package com.proyek.akpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String GetUserID;
    private static final String USERS = "message";
    private String getnim;
    private String KEY_NAME = "Nim";
    private TextInputEditText getnama, getangkatan, getemail, getpass, getsaldo, getjurusan;
    private ImageView showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        //Mendapatkan User ID dari akun yang terautentikasi
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        //Mengambil NIM dari Activity sebelumnya
        Bundle exstras = getIntent().getExtras();
        getnim = exstras.getString(KEY_NAME);

        //Deklarasi edit Nama,Angkatan,Email,Pass
        getnama = findViewById(R.id.getnama);
        getangkatan = findViewById(R.id.getangkatan);
        getemail = findViewById(R.id.getemail);
        getpass = findViewById(R.id.getpassword);
        getsaldo = findViewById(R.id.getsaldo);
        getjurusan = findViewById(R.id.getjurusan);
        showpass = findViewById(R.id.show_pass_btn);

        //Perkondisian Enabled or Disabled Input
        getnama.setFocusable(false);
        getnama.setEnabled(false);
        getnama.setCursorVisible(false);
        getnama.setKeyListener(null);

        getangkatan.setFocusable(false);
        getangkatan.setEnabled(false);
        getangkatan.setCursorVisible(false);
        getangkatan.setKeyListener(null);

        getemail.setFocusable(false);
        getemail.setEnabled(false);
        getemail.setCursorVisible(false);
        getemail.setKeyListener(null);

        getpass.setFocusable(false);
        getpass.setEnabled(false);
        getpass.setCursorVisible(false);

        getsaldo.setFocusable(false);
        getsaldo.setEnabled(false);
        getsaldo.setCursorVisible(false);
        getsaldo.setKeyListener(null);

        getjurusan.setFocusable(false);
        getjurusan.setEnabled(false);
        getjurusan.setCursorVisible(false);
        getjurusan.setKeyListener(null);

        userRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Mengambil 2 angka dari NIM: (18)1057009.
                String nimDum = getnim.substring(0,2).toString().trim();
                //Mengambil 2 angka dari NIM: 181(05)7009.
                String nimDumJur = getnim.substring(3,5).toString().trim();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //Perkondisian jika nim sama dengan nim database, maka ambil data dari database.
                    if (ds.child("nim").getValue().equals(getnim)) {
                        getnama.setText(ds.child("nam").getValue(String.class).toString().trim());
                        getpass.setText(ds.child("nim").getValue(String.class).toString().trim());
                        getemail.setText(ds.child("email").getValue(String.class).toString().trim());
                        getsaldo.setText(ds.child("saldo").getValue(Integer.class).toString().trim());
                        //Perkondisian Jurusan dengan 2 Angka NIM 181(05)7009
                        if(nimDumJur.equals("01")){
                            getjurusan.setText("Teknik Kimia");
                        }
                        else if(nimDumJur.equals("02")){
                            getjurusan.setText("Teknik Industri");
                        }
                        else if(nimDumJur.equals("03")){
                            getjurusan.setText("Teknik Mesin");
                        }
                        else if(nimDumJur.equals("04")){
                            getjurusan.setText("Teknik Elektro");
                        }
                        else if(nimDumJur.equals("05")){
                            getjurusan.setText("Informatika");
                        }
                        else if(nimDumJur.equals("06")){
                            getjurusan.setText("Statistika");
                        }
                        else if(nimDumJur.equals("07")){
                            getjurusan.setText("Rekayasa Sistem Komputer");
                        }
                        else if(nimDumJur.equals("10")){
                            getjurusan.setText("Teknik Geologi");
                        }
                        else if(nimDumJur.equals("11")){
                            getjurusan.setText("Teknik Lingkungan");
                        }
                        else if(nimDumJur.equals("12")){
                            getjurusan.setText("Bisnis Digital");
                        };

                        //Perkondisian Angkatan dengan 2 Angka NIM (18)1057009
                        if(nimDum.equals("14")){
                            getangkatan.setText("2014");
                        }
                        else if(nimDum.equals("15")){
                            getangkatan.setText("2015");
                        }
                        else if(nimDum.equals("16")){
                            getangkatan.setText("2016");
                        }
                        else if(nimDum.equals("17")){
                            getangkatan.setText("2017");
                        }
                        else if(nimDum.equals("18")){
                            getangkatan.setText("2018");
                        }
                        else if(nimDum.equals("19")){
                            getangkatan.setText("2019");
                        };
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //ShowHidePassword
        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showpass.getId() == R.id.show_pass_btn) {
                    if (getpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_visibility_off_24);
                        //Show Password
                        getpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (showpass)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                        //Hide Password
                        getpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String dataku = getnim;
                switch (item.getItemId()) {
                    case R.id.Home:
                        Intent rumah = new Intent(getApplicationContext(), MainActivity.class);
                        rumah.putExtra(KEY_NAME, dataku);
                        startActivity(rumah);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Topup:
                        Intent topup = new Intent(getApplicationContext(), Topup.class);
                        topup.putExtra(KEY_NAME, dataku);
                        startActivity(topup);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.trans:
                        Intent trans = new Intent(getApplicationContext(), Transaksi.class);
                        trans.putExtra(KEY_NAME, dataku);
                        startActivity(trans);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.account:
                        return true;
                }
                return false;
            }
        });

    }
}