package com.example.go_chat_android.api;

import androidx.lifecycle.MutableLiveData;

import com.example.go_chat_android.Contact;
import com.example.go_chat_android.ContactDao;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.entities.LoginInfo;
import com.example.go_chat_android.entities.User;

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

    public APIService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }

    public void get() {
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

    public String login(LoginInfo loginInfo) {
        Call<LoginInfo> call = webServiceApi.login(loginInfo);
        String token = "";
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                if (response.isSuccessful()) {
                    LoginInfo token = response.body();
                } else {
//                    token = "";
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {

            }
        });
        return token;
    }

    public String register(User user) {
        Call<User> call = webServiceApi.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return null;
    }
}
