package com.example.go_chat_android.api;

import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.Message;
import com.example.go_chat_android.entities.User;
import com.example.go_chat_android.entities.contactFields;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {
    @GET("Contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String token);

    @POST("Contacts")
    Call<Void> addContact(@Body contactFields contact, @Header("Authorization") String token);

    @POST("Users/Login")
    Call<String> login(@Body LoginFields loginFields);

    @POST("Users/Register")
    Call<String> register(@Body User user);

    @GET("Contacts/{id}/Messages/")
    Call<List<Message>> getMessages(@Path("id") String id,@Header("Authorization") String token);

    @POST("Contacts/{id}/Messages/")
    Call<Void> addMessage(@Path("id") String id, @Body String content,@Header("Authorization") String token);
}
