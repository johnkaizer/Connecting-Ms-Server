package com.project.ms_server_connection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void password(View view) {
    }

    public void login(View view) {
        startActivity(new Intent(SignIn.this, MainActivity.class));
        finish();
    }

    public void sign_Up(View view) {
        startActivity(new Intent(SignIn.this, SignUp.class));
        finish();
    }
}