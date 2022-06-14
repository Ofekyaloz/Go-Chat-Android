package com.example.go_chat_android.api;

import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.Message;
import com.example.go_chat_android.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {
    @GET("Contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String str);

    @POST("Contacts")
    Call<Void> addContacts(@Body Contact contact, @Header("Authorization") String str);

    @GET("Contacts/{id}")
    Call<Contact> getContact(@Path("id") String id, @Header("Authorization") String str);

    @POST("Users/Login")
    Call<String> login(@Body LoginFields loginFields);

    @POST("Users/Register")
    Call<String> register(@Body User user);

    @GET("Contacts/{id}/Messages/")
    Call<List<Message>> getMessages(@Header("Authorization") String str);

    @POST("Contacts/{id}/Messages/")
    Call<List<Message>> addMessage(String id, @Body String content,@Header("Authorization") String str);
}
