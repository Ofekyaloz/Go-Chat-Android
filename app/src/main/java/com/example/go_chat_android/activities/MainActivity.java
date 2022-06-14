package com.example.go_chat_android.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.go_chat_android.Common;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.api.APIService;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.databinding.ActivityMainBinding;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.lists.ContactList;
import com.example.go_chat_android.viewmodels.SampleViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private SampleViewModel contacts;
    private APIService contactAPI;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;

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
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            webServiceApi = retrofit.create(WebServiceApi.class);
            LoginFields loginFields = new LoginFields(username, password);
            Call<String> call = webServiceApi.login(loginFields);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        mainBinding.loginTvError.setVisibility(View.INVISIBLE);
                        Common.token = response.body();
                        Intent intent = new Intent(getApplicationContext(), ContactList.class);
                        startActivity(intent);
                    } else {
                        mainBinding.loginTvError.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    mainBinding.loginTvError.setVisibility(View.VISIBLE);
                }

            });

        });
    }
}