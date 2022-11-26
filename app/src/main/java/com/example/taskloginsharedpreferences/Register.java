package com.example.taskloginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.taskloginsharedpreferences";

    private final static String NAME_KEY = "name-key";
    private final static String EMAIL_KEY = "email-key";
    private final static String PASS_KEY = "pass-key";
    private final static String TTL_KEY = "ttl-key";
    private final static String NUMBER_KEY = "number-key";

    private EditText nama, email, pass, tanggalLahir, noHP;
    private Button btn_lanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = findViewById(R.id.nama_lengkap);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        tanggalLahir = findViewById(R.id.tgl_lahir);
        noHP = findViewById(R.id.no_hp);
        btn_lanjut = findViewById(R.id.btn_next);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        tanggalLahir.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDatePickerTtl();
             }
         });

         btn_lanjut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (TextUtils.isEmpty(nama.getText().toString())) {
                     Toast.makeText(Register.this, "Kolom Nama tidak boleh kosong !",
                             Toast.LENGTH_SHORT).show();
                 }

                 else if (TextUtils.isEmpty(email.getText().toString())) {
                     Toast.makeText(Register.this, "Kolom Email tidak boleh kosong !",
                             Toast.LENGTH_SHORT).show();
                 }

                 else if (TextUtils.isEmpty(pass.getText().toString())) {
                     Toast.makeText(Register.this, "Kolom Password tidak boleh kosong !",
                             Toast.LENGTH_SHORT).show();
                 }

                 else if (TextUtils.isEmpty(tanggalLahir.getText().toString())) {
                     Toast.makeText(Register.this, "Kolom Tanggal Lahir tidak boleh kosong !",
                             Toast.LENGTH_SHORT).show();
                 }

                 else if (TextUtils.isEmpty(noHP.getText().toString())) {
                     Toast.makeText(Register.this, "Kolom NO HP tidak boleh kosong !",
                             Toast.LENGTH_SHORT).show();
                 }

                 else {
                     saveData();
                     Toast.makeText(Register.this, "Berhasil Mendaftar !",
                             Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(Register.this, Login.class);
                     startActivity(intent);
                 }
             }
         });
    }

    public void showDatePickerTtl() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    public void processDatePickerResultTtl(int day, int month, int year) {

        String dayString = Integer.toString(day);
        String monthString = Integer.toString(month + 1);
        String yearString = Integer.toString(year);

        String dateMessage = dayString + "/" + monthString + "/" + yearString;
        tanggalLahir.setText(dateMessage);
    }

    private void saveData() {
        String nameValue = nama.getText().toString();
        String emailValue = email.getText().toString();
        String passValue = pass.getText().toString();
        String numberValue = noHP.getText().toString();
        String ttlValue = tanggalLahir.getText().toString();

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(NAME_KEY, nameValue);
        editor.putString(EMAIL_KEY, emailValue);
        editor.putString(PASS_KEY, passValue);
        editor.putString(NUMBER_KEY, numberValue);
        editor.putString(TTL_KEY, ttlValue);
        editor.apply();
    }


}