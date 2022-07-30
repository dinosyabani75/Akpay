package com.proyek.akpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RincianPembayaran extends AppCompatActivity {
    private String KEY_jur = "Jurusan";
    private String KEY_NAME = "Nim";
    private String KEY_Jen = "jenis";
    private String KEY_sks = "sks";
    private String Jrsn, nim ;
    private TextView text1;
    private String[] chose = {"-Pilih-", "Tetap", "Variabel"};
    private Button btn, btn2;
    private EditText tx1;
    private String sks = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pembayaran);

        Bundle extras = getIntent().getExtras();
        Jrsn = extras.getString(KEY_jur);
        Bundle extras2 = getIntent().getExtras();
        nim = extras2.getString(KEY_NAME);

        text1 = (TextView) findViewById(R.id.textView9);
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, chose);
        tx1 = (EditText) findViewById(R.id.jumlah_sks);
        text1.setText(Jrsn);
        btn = (Button) findViewById(R.id.button6);
        btn2 = (Button) findViewById(R.id.button7);
        sp1.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = sp1.getSelectedItemPosition();
                String edt = tx1.getText().toString().trim();
                String dataku = nim;
                String data1 = Jrsn;
                String eskaes = sks;
                String jenis = chose[index];
                if (chose[index] == "Variabel") {
                    tx1.setVisibility(View.VISIBLE);
                    if (edt.equals("")) {
                        Toast.makeText(RincianPembayaran.this, "Silahkan Masukkan SKS terlebih dahulu", Toast.LENGTH_SHORT).show();
                    } else {
                        eskaes = tx1.getText().toString().trim();
                        Intent i = new Intent(getApplication(), Pembayaran_Lanjutan.class);
                        i.putExtra(KEY_sks,eskaes);
                        i.putExtra(KEY_NAME, dataku);
                        i.putExtra(KEY_Jen, jenis);
                        i.putExtra(KEY_jur, data1);
                        startActivity(i);
                    }
                } else {
                    tx1.setText("");
                    tx1.setVisibility(View.INVISIBLE);
                    Intent i = new Intent(getApplication(), Pembayaran_Lanjutan.class);
                    i.putExtra(KEY_NAME, dataku);
                    i.putExtra(KEY_Jen, jenis);
                    i.putExtra(KEY_jur, data1);
                    startActivity(i);

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataku = nim;
                String dataku1 = Jrsn;
                Intent bck = new Intent(getApplicationContext(), MainActivity.class);
                bck.putExtra(KEY_NAME, dataku);
                bck.putExtra(KEY_jur, dataku1);
                startActivity(bck);
            }
        });
    }
}