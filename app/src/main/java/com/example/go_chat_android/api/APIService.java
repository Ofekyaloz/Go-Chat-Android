package com.example.go_chat_android.api;

import androidx.lifecycle.MutableLiveData;

import com.example.go_chat_android.Common;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private MutableLiveData<List<Contact>> contactListData;
    private ContactDao dao;
    Retrofit retrofit;
    WebServiceApi webServiceApi;
    Gson gson;

    public APIService() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);

    }

    public void get(String token) {
        Call<List<Contact>> call = webServiceApi.getContacts("Bearer " + token);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }

    public void login(LoginFields loginFields) {
        Call<String> call = webServiceApi.login(loginFields);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Common.token = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String tmp = "";
            }
        });
    }

    public void register(User user) {
        Call<String> call = webServiceApi.register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Common.token = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
