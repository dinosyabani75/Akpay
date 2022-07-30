package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String KEY_NAME = "Nim";
    private String KEY_jur = "Jurusan";
    private String nim, jurusan;
    private TextView jur1, jur2, jur3, jur4, jur5, jur6, jur7, jur8, jur9, jur10, jur11, jur12;
    private CardView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12;

    // private TextView page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //page = (TextView)findViewById(R.id.textView7);
        Bundle exstras = getIntent().getExtras();
        nim = exstras.getString(KEY_NAME);
        Bundle exstras1 = getIntent().getExtras();
        jurusan = exstras1.getString(KEY_jur);

        jur1 = (TextView) findViewById(R.id.txt1);
        jur2 = (TextView) findViewById(R.id.txt2);
        jur3 = (TextView) findViewById(R.id.txt3);
        jur4 = (TextView) findViewById(R.id.txt4);
        jur5 = (TextView) findViewById(R.id.txt5);
        jur6 = (TextView) findViewById(R.id.txt6);
        jur7 = (TextView) findViewById(R.id.txt7);
        jur8 = (TextView) findViewById(R.id.txt8);
        jur9 = (TextView) findViewById(R.id.txt9);
        jur10 = (TextView) findViewById(R.id.txt10);
        jur11 = (TextView) findViewById(R.id.txt11);
        jur12 = (TextView) findViewById(R.id.txt12);

        card1 = (CardView) findViewById(R.id.cv_1);
        card2 = (CardView) findViewById(R.id.cv_2);
        card3 = (CardView) findViewById(R.id.cv_3);
        card4 = (CardView) findViewById(R.id.cv_4);
        card5 = (CardView) findViewById(R.id.cv_5);
        card6 = (CardView) findViewById(R.id.cv_6);
        card7 = (CardView) findViewById(R.id.tes);
        card8 = (CardView) findViewById(R.id.cv_8);
        card9 = (CardView) findViewById(R.id.cv_9);
        card10 = (CardView) findViewById(R.id.cv_10);
        card11 = (CardView) findViewById(R.id.cv_11);
        card12 = (CardView) findViewById(R.id.cv_12);


        //page.setText(nim);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur1.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur2.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur3.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur4.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur5.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur6.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur7.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur8.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur9.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur10.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur11.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });
        card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat = jur12.getText().toString();
                Intent int1 = new Intent(getApplicationContext(), RincianPembayaran.class);
                int1.putExtra(KEY_jur, dat);
                int1.putExtra(KEY_NAME, nim);
                startActivity(int1);
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String dataku = nim;

                switch (item.getItemId()) {
                    case R.id.Home:
                        return true;
                    case R.id.Topup:
                        Intent topup = new Intent(getApplicationContext(), Topup.class);
                        topup.putExtra(KEY_NAME, dataku);
                        topup.putExtra(KEY_jur, jurusan);
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
                        Intent akun = new Intent(getApplicationContext(), Profile.class);
                        akun.putExtra(KEY_NAME, dataku);
                        akun.putExtra(KEY_jur, jurusan);
                        startActivity(akun);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
}