package com.example.taskloginsharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.taskloginsharedpreferences";

    private final static String EMAIL_KEY = "email-key";
    private final static String PASS_KEY = "pass-key";

    private EditText id, pass;
    private Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        masuk = findViewById(R.id.btn_login);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = id.getText().toString();
                String passwordValue = pass.getText().toString();

                String emailRegistered = mSharedPref.getString(EMAIL_KEY,null);
                String passRegistered = mSharedPref.getString(PASS_KEY,null);

                if ((emailValue.equals(emailRegistered)) && (passwordValue.equals(passRegistered))) {
                    Toast.makeText(Login.this, "Login Berhasil!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                }

                else if (TextUtils.isEmpty(id.getText().toString())) {
                    Toast.makeText(Login.this, "Kolom Username tidak boleh kosong !",
                            Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(Login.this, "Kolom Password tidak boleh kosong !",
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    showAlertDialog();
                }
            }
        });
    }

    public void showAlertDialog () {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Login.this);
        alertBuilder.setTitle("Login Gagal");
        alertBuilder.setMessage("Username atau password salah, silahkan coba lagi!");

        alertBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pass.getText().clear();
            }
        });
        alertBuilder.show();
    }

    public void tvRegister(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}