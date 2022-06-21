package com.example.go_chat_android.api;

import androidx.lifecycle.MutableLiveData;

import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.Content;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.MessageClass;
import com.example.go_chat_android.entities.RegisterUser;
import com.example.go_chat_android.entities.contactFields;
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
                .baseUrl(MyApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);

    }

//    public void get(String token) {
//        Call<List<Contact>> call = webServiceApi.getContacts("Bearer " + token);
//        call.enqueue(new Callback<List<Contact>>() {
//            @Override
//            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
//                MyApplication.contactList = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Contact>> call, Throwable t) {
//            }
//        });
//    }

    public void login(LoginFields loginFields) {
        Call<String> call = webServiceApi.login(loginFields);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    MyApplication.token = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String tmp = "";
            }
        });
    }

    public void register(RegisterUser user) {
        Call<String> call = webServiceApi.register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    MyApplication.token = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void getMessages(String contact, String token) {
        Call<List<MessageClass>> call = webServiceApi.getMessages(contact,"Bearer " + token);
        call.enqueue(new Callback<List<MessageClass>>() {
            @Override
            public void onResponse(Call<List<MessageClass>> call, Response<List<MessageClass>> response) {
                if (response.isSuccessful()) {
                    List<MessageClass> messages = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<MessageClass>> call, Throwable t) {

            }
        });
    }

    public void addMessage(String contact, String token, String content) {
        Content content1 = new Content(content);
        Call<Void> call = webServiceApi.addMessage(contact, content1,token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addContact(contactFields contactFields, String token) {
        Call<Void> call = webServiceApi.addContact(contactFields, "Bearer " + token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
