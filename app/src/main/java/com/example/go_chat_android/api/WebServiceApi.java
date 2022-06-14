package com.example.go_chat_android.api;

import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceApi {
    @GET("Contacts")
    Call<List<Contact>> getContacts();

    @POST("Contacts")
    Call<Void> addContacts(@Body Contact contact);

    @GET("Contacts/{id}")
    Call<Contact> getContact(@Path("id") String id, @Header("Authorization") String str);

    @PUT("Contacts/{id}")
    Call<Contact> editContact(@Path("id") String id);

    @DELETE("Contacts/{id}")
    Call<Contact> deleteContact(@Path("id") String id);

    @POST("Users/Login")
    Call<String> login(@Body LoginFields loginFields);

    @POST("Users/Register")
    Call<String> register(@Body User user);

//    @POST("Contacts/{id}/Messages/")

}
