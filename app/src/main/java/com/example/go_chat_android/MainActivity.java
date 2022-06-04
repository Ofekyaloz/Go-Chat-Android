package com.example.go_chat_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_chat_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        mainBinding.btnLogin.setOnClickListener(v -> {
            String Username = mainBinding.loginEtUsername.getText().toString();
            String Password = mainBinding.loginEtPassword.getText().toString();

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}