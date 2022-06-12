package com.example.go_chat_android.api;

import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.LoginInfo;
import com.example.go_chat_android.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    Retrofit retrofit;
    WebServiceApi webServiceApi;

    public APIService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }

    public void getContacts() {
        Call<List<Contact>> call = webServiceApi.getContacts();
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

    public void login(LoginInfo loginInfo) {
        Call<LoginInfo> call = webServiceApi.login(loginInfo);
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                String tmp = "";
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                String tmp = "";
            }
        });
    }

    public String register(User user) {
        Call<String> call = webServiceApi.register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return "";
    }


}
