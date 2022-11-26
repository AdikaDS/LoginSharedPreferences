package com.example.taskloginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.taskloginsharedpreferences";

    private final static String NAME_KEY = "name-key";
    private final static String TTL_KEY = "ttl-key";
    private final static String NUMBER_KEY = "number-key";

    private TextView txtNama, txtTtl, txtNomer;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtNama = findViewById(R.id.txt_name);
        txtTtl = findViewById(R.id.txt_ttl);
        txtNomer = findViewById(R.id.txt_no);
        btnLogout = findViewById(R.id.btn_logout);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        String name = mSharedPref.getString(NAME_KEY, null);
        String ttl = mSharedPref.getString(TTL_KEY, null);
        String no = mSharedPref.getString(NUMBER_KEY, null);

        txtNama.setText("Nama Lengkap : " + name);
        txtNomer.setText("No HP : " + no);
        txtTtl.setText("Tanggal Lahir : " + ttl);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logout();
            }
        });
    }

    private void logout() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.clear();
        editor.apply();
        finish();
        Toast.makeText(Dashboard.this, "Logout Berhasil !",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Dashboard.this, Login.class);
        startActivity(intent);
    }
}