package com.example.go_chat_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_chat_android.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        mainBinding.btnGotoRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        mainBinding.btnLogin.setOnClickListener(v -> {
            String username = mainBinding.loginEtUsername.getText().toString();
            String password = mainBinding.loginEtPassword.getText().toString();
            if (!Pattern.matches("[A-Za-z0-9]{4,30}$", username) ||
                    !Pattern.matches("[A-Za-z0-9]{4,30}$", password)) {
                mainBinding.loginTvError.setVisibility(View.VISIBLE);
                return;
            }
            mainBinding.loginTvError.setVisibility(View.INVISIBLE);
        });
    }
}