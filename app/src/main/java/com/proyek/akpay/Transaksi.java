package com.proyek.akpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transaksi extends AppCompatActivity {
    private String KEY_NAME = "Nim";
    private static final String USERS = "message";
    private static final String USERS2 = "Transaksi";
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference userRef2;
    private String GetUserID;
    private String nim,z;
    private EditText a;
    private TextView b,c,d,e,f,g;
    private Button but_cek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        auth = FirebaseAuth.getInstance();
        //Mendapatkan User ID dari akun yang terautentikasi
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
        database = FirebaseDatabase.getInstance();
        userRef2 = database.getReference(USERS2);
        DatabaseReference myRef = database.getReference("transaksi");

        Bundle exstras = getIntent().getExtras();
        nim = exstras.getString(KEY_NAME);
        a = findViewById(R.id.jumlah_sks);
        b = findViewById(R.id.jumlah_sks2);
        c = findViewById(R.id.jumlah_sks3);
        d = findViewById(R.id.jumlah_sks4);
        e = findViewById(R.id.jumlah_sks5);
        f = findViewById(R.id.jumlah_sks6);
        g = findViewById(R.id.jumlah_sks7);
        z = "8371344851719";
        but_cek = findViewById(R.id.button6);



        but_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                z = a.getText().toString();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("kode").getValue().equals(z)) {
                                b.setText(ds.child("bank").getValue(String.class).toString().trim());
                                c.setText(ds.child("berita").getValue(String.class).toString().trim());
                                d.setText(ds.child("keterangan").getValue(String.class).toString().trim());
                                g.setText(ds.child("kode").getValue(String.class).toString().trim());
                                e.setText(ds.child("nim").getValue(String.class).toString().trim());
                                f.setText(ds.child("total").getValue(String.class).toString().trim());
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Transaksi.this, "Gak Konek Dbase nya",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.trans);
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
                        Intent topup = new Intent(getApplicationContext(), Topup.class);
                        topup.putExtra(KEY_NAME, dataku);
                        startActivity(topup);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.trans:
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
}