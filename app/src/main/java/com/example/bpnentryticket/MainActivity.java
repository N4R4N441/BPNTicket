package com.example.bpnentryticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText et_nomorbooking;
    Button btn_submit;
    ArrayList<String> list;
    ArrayList<String> list2;
    Pendaftaran daftar;
    Tiket tiket;
    int Stopper;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        et_nomorbooking = findViewById(R.id.et_nobooking);
        btn_submit = findViewById(R.id.btn_submit);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Pendaftaran");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiket = new Tiket();
                daftar = new Pendaftaran();
                Stopper = 0;
                String nomorbooking = et_nomorbooking.getText().toString();
                if (nomorbooking.isEmpty()) {
                    et_nomorbooking.setError("Harap Masukkan Nomor Booking");
                    et_nomorbooking.requestFocus();
                } else {
                    coba(et_nomorbooking.getText().toString());
                }

            }
        });

    }

    private void coba(String asd1){
        reference.child(asd1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() != 0){
                    daftar = snapshot.getValue(Pendaftaran.class);
                    if(!daftar.getStatus().equals("SELESAI")){
                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Antrian");
                        mRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                tiket = snapshot.getValue(Tiket.class);
                                String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
                                if (tiket.getHari() == Integer.parseInt(date)) {
                                    if (Stopper == 0) {
                                        int nomorantri = tiket.getNoAntrian();
                                        nomorantri++;
                                        tiket.setNoAntrian(nomorantri);
                                        tiket.setHari(Integer.parseInt(date));
                                        postnomorantrian(tiket);
                                        Stopper++;
                                    }

                                } else {
                                    if (Stopper == 0) {
                                        tiket.setHari(Integer.parseInt(date));
                                        tiket.setNoAntrian(0);
                                        postnomorantrian(tiket);
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        Toast.makeText(MainActivity.this, "Sudah di gunakan", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void postnomorantrian(Tiket tiket) {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Antrian");
        mRef.setValue(tiket);
        DatabaseReference mRefOrang = FirebaseDatabase.getInstance().getReference("Pendaftaran").child(daftar.getNomor_booking());
        daftar.setStatus("SELESAI");
        daftar.setNomor_antrian(String.valueOf(tiket.getNoAntrian()));
        mRefOrang.setValue(daftar);
        Intent intent = new Intent(getApplicationContext(),NomorAntrianBerhasilActivity.class);
        intent.putExtra("uuid", daftar.getUuid());
        intent.putExtra("no", daftar.getNomor_antrian());
        startActivity(intent);
        finish();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}