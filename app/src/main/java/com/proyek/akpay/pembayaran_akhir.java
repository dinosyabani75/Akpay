package com.proyek.akpay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pembayaran_akhir extends AppCompatActivity {
    private static final String USERS = "message";
    private final String KEY_Jur = "Jurusan";
    private final String KEY_NAME = "Nim";
    private final String KEY_Jen = "jenis";
    private final String KEY_sks = "sks";
    private final String KEY_kode= "kode";
    private String Jrsn, nim, sks, jenis;
    private String[] chose = {"-Pilih-", "Mandiri", "Bank BPD Yogyakarta"};
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private TextInputEditText nim_bayar, jumlah_uang, rincian_uang, berita;
    private Button bayar;
    private TextView note, n1,n2,n3,n4;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_akhir);

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
        DatabaseReference myRef = database.getReference("transaksi");

        //Deklarasi variabel
        nim_bayar = findViewById(R.id.rincian_NIM);
        jumlah_uang = findViewById(R.id.rincian_uang);
        rincian_uang = findViewById(R.id.rincian_uang1);
        berita = findViewById(R.id.rincian_berita);
        bayar = findViewById(R.id.bayar);
        note = findViewById(R.id.textView12);
        n1 = findViewById(R.id.textView13);
        n2 = findViewById(R.id.textView14);
        n3 = findViewById(R.id.textView15);
        n4 = findViewById(R.id.textView16);
        final Spinner sp1 = findViewById(R.id.spinner_bank);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, chose);
        sp1.setAdapter(adapter);

        nim_bayar.setText(nim);

        note.setText("Pembayaran Spp "+jenis+" Jurusan "+Jrsn+" Sebanyak "+sks+" Sks");


        //Perkondisian Enabled or Disabled Input
        nim_bayar.setFocusable(false);
        nim_bayar.setEnabled(false);
        nim_bayar.setCursorVisible(false);

        jumlah_uang.setFocusable(true);
        jumlah_uang.setEnabled(true);
        jumlah_uang.setCursorVisible(true);

        rincian_uang.setFocusable(false);
        rincian_uang.setEnabled(false);
        rincian_uang.setCursorVisible(false);

        berita.setFocusable(true);
        berita.setEnabled(true);
        berita.setCursorVisible(true);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("nim").getValue().equals(nim)){
                        n1.setText(ds.child("nim").getValue(String.class).toString().trim());
                        n2.setText(ds.child("nam").getValue(String.class).toString().trim());
                        n3.setText(ds.child("email").getValue(String.class).toString().trim());
                        n4.setText(ds.child("saldo").getValue(Integer.class).toString().trim());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        total();

        bayar.setOnClickListener(v -> {
            String a1 = jumlah_uang.getText().toString();
            String a2 = n4.getText().toString();
            String a3 = rincian_uang.getText().toString();
            int b1 = Integer.parseInt(a1);
            int b2 = Integer.parseInt(a2);
            int b3 = Integer.parseInt(a3);
            if (b1>b2){
                Toast.makeText(pembayaran_akhir.this, "Saldo Anda kurang gan, silahkan topup dulu",
                        Toast.LENGTH_SHORT).show();

            } else if (b1>b3){
                Toast.makeText(pembayaran_akhir.this, "Kelebihan gan, Pake Uang pas aja mas/mba",
                        Toast.LENGTH_SHORT).show();
            } else if (b1<b3) {
                Toast.makeText(pembayaran_akhir.this, "Kurang gan, silahkan top up dulu",
                        Toast.LENGTH_SHORT).show();
            } else {
                //Verifikasi Ke Email
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"instagramranger75@gmail.com"});
                intent.putExtra(Intent.EXTRA_CC, String.valueOf(n3));
                intent.putExtra(Intent.EXTRA_SUBJECT, "VERIFIKASI PEMBAYARAN");
                intent.putExtra(Intent.EXTRA_TEXT, "History Pembayaran: "+ berita + "Pembayaran Spp " + jenis + " Jurusan " + Jrsn + " Sebanyak "+sks+" Sks");
                try {
                    startActivity(Intent.createChooser(intent, "KIRIM VERIFIKASI PEMBAYARAN MELALUI EMAIL"));
                } catch (android.content.ActivityNotFoundException ex) {
                    //do something else
                }

                long mDateTime = 9999999999999L - System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);
                int total =b2-b1;
                String nama,nim,email;
                nama = n2.getText().toString();
                nim = n1.getText().toString();
                email = n3.getText().toString();
                DataItem setdataitem = new DataItem(nama,nim,email,total);
                userRef.child(nim).setValue(setdataitem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(pembayaran_akhir.this, "Berhasil Bayar.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                String z1 = nim_bayar.getText().toString();
                int index = sp1.getSelectedItemPosition();
                String z2 = chose[index];
                String z3 = berita.getText().toString();
                String z4 = rincian_uang.getText().toString();
                String z5 = note.getText().toString();
                String z0 = mOrderTime;
                DataItem2 dataItem2 = new DataItem2(z0,z1, z2, z3, z4, z5);

                //Update Saldo Ke Database
                myRef.child(mOrderTime).setValue(dataItem2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(pembayaran_akhir.this, "Bayar Berhasil Gan",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Intent i = new Intent(getApplication(), Splash_Topup.class);
                i.putExtra(KEY_kode,mOrderTime);
                i.putExtra(KEY_NAME,nim);
                i.putExtra(KEY_Jur,Jrsn);
                startActivity(i);
            }
        });
    }
    public void total(){
        int spptetap, sppvariabel;

        //Pembayaran SPP Tetap
        if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Kimia")){
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Industri")) {
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Mesin")) {
            spptetap=250000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Elektro")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Informatika")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Statistika")) {
            spptetap=275000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Rekayasa Sistem Komputer")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Geologi")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Teknik Lingkungan")) {
            spptetap=290000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[S1] Bisnis Digital")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[D3] Teknologi Mesin")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        else if (jenis.equals("Tetap")&&Jrsn.equals("[D3] Teknologi Industri")) {
            spptetap=300000;
            String getSpptetap = String.valueOf(spptetap);
            rincian_uang.setText(getSpptetap);
        }
        //Pembayaran SPP Variabel
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Kimia")){
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Industri")) {
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Mesin")) {
            sppvariabel=120000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Elektro")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Informatika")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Statistika")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Rekayasa Sistem Komputer")) {
            sppvariabel=125000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Geologi")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Teknik Lingkungan")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[S1] Bisnis Digital")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[D3] Teknologi Mesin")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
        else if (jenis.equals("Variabel")&&Jrsn.equals("[D3] Teknologi Industri")) {
            sppvariabel=130000;
            int totalsks = Integer.parseInt(sks);
            int totalbayar=sppvariabel*totalsks;
            String totalbayar1 = String.valueOf(totalbayar);
            rincian_uang.setText(totalbayar1);
        }
    }
}