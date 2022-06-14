package com.example.go_chat_android.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.go_chat_android.api.APIService;
import com.example.go_chat_android.databinding.ActivityMainBinding;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.lists.ContactList;
import com.example.go_chat_android.viewmodels.SampleViewModel;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private SampleViewModel contacts;
    private APIService contactAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        contactAPI = new APIService();

        contacts = new ViewModelProvider(this).get(SampleViewModel.class);


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
            LoginFields loginFields = new LoginFields(username, password);
            contactAPI.login(loginFields);
            Intent intent = new Intent(getApplicationContext(), ContactList.class);
            startActivity(intent);

        });
    }
}