package com.a2v10.spamemaildetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login();

    }

    public void login(View view) {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        i.putExtra("email", email.getText().toString());
        i.putExtra("pass", password.getText().toString());
        startActivity(i);

    }
    public void login() {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        i.putExtra("email", "");
        i.putExtra("pass", "");
        startActivity(i);

    }
}