package com.proyek.akpay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pembayaran_Lanjutan extends AppCompatActivity {
    private static final String USERS = "message";
    private final String KEY_Jur = "Jurusan";
    private final String KEY_NAME = "Nim";
    private final String KEY_Jen = "jenis";
    private final String KEY_sks = "sks";
    private String Jrsn, nim, sks, jenis;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private TextInputEditText nama, nm, jurusan, jenispem, totalpem;
    private String min;
    private Button proses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran__lanjutan);

        Bundle extras = getIntent().getExtras();
        Jrsn = extras.getString(KEY_Jur);
        Bundle extras2 = getIntent().getExtras();
        nim = extras2.getString(KEY_NAME);
        Bundle extras3 = getIntent().getExtras();
        jenis = extras3.getString(KEY_Jen);
        Bundle extras4 = getIntent().getExtras();
        sks = extras4.getString(KEY_sks);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        Log.v("NIMADD", userRef.orderByChild("nim").equalTo(min).toString());

        //Deklarasi edit Nama,Angkatan,Email,Pass
        nama = findViewById(R.id.rincian_nama);
        nm = findViewById(R.id.rincian_nim);
        jurusan = findViewById(R.id.rincian_jurusan);
        jenispem = findViewById(R.id.rincian_jenispem);
        totalpem = findViewById(R.id.rincian_totalpem);
        proses = findViewById(R.id.button8);

        //Perkondisian Enabled or Disabled Input
        nama.setFocusable(false);
        nama.setEnabled(false);
        nama.setCursorVisible(false);

        nm.setFocusable(false);
        nm.setEnabled(false);
        nm.setCursorVisible(false);

        jurusan.setFocusable(false);
        jurusan.setEnabled(false);
        jurusan.setCursorVisible(false);

        jenispem.setFocusable(false);
        jenispem.setEnabled(false);
        jenispem.setCursorVisible(false);
        jenispem.setKeyListener(null);
        jenispem.setInputType(InputType.TYPE_NULL);

        totalpem.setFocusable(false);
        totalpem.setEnabled(false);
        totalpem.setCursorVisible(false);
        totalpem.setKeyListener(null);
        totalpem.setInputType(InputType.TYPE_NULL);

        proses.setOnClickListener(v -> {
            String a = nim;
            String b = Jrsn;
            String c = jenis;
            String d = sks;
            Intent i = new Intent(getApplication(), pembayaran_akhir.class);
            i.putExtra(KEY_NAME, a);
            i.putExtra(KEY_Jur, b);
            i.putExtra(KEY_Jen, c);
            i.putExtra(KEY_sks, d);
            startActivity(i);
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("nim").getValue().equals(nim)) {
                        nama.setText(ds.child("nam").getValue(String.class));
                        nm.setText(nim);
                        jurusan.setText(Jrsn);
                        jenispem.setText(jenis +" "+" Sks "+"("+ sks +")");
                        total();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void total(){
        int spptetap, sppvariabel;

        //Pembayaran SPP Tetap
        if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Kimia")){
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Industri")) {
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Mesin")) {
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Elektro")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Informatika")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Statistika")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Rekayasa Sistem Komputer")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Geologi")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Lingkungan")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Bisnis Digital")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[D3] Teknologi Mesin")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[D3] Teknologi Industri")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            totalpem.setText(getSpptetap);
        }
        //Pembayaran SPP Variabel
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Kimia")){
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Industri")) {
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Mesin")) {
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Elektro")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Informatika")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Statistika")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Rekayasa Sistem Komputer")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Geologi")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Lingkungan")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Bisnis Digital")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[D3] Teknologi Mesin")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[D3] Teknologi Industri")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            totalpem.setText(totalbayar1);
        }
    }

}