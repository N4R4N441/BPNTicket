package com.example.bpnentryticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NomorAntrianBerhasilActivity extends AppCompatActivity {

    String uuid, noantri;
    TextView nama, nomor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nomor_antrian_berhasil);
        Bundle extra = getIntent().getExtras();
        nama = findViewById(R.id.nama);
        nomor = findViewById(R.id.nomor);
        if(extra == null){
            uuid = null;
            noantri = null;
        }else{
            uuid = extra.getString("uuid");
            noantri = extra.getString("no");
            getData(uuid);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },10000);
    }

    private void getData(String uuid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(uuid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nama.setText("Hallo, "+snapshot.child("nama").getValue(String.class));
                nomor.setText(noantri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}