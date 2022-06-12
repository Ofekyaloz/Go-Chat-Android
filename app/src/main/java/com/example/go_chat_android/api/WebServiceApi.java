package com.example.go_chat_android.api;

import com.example.go_chat_android.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceApi {
    @GET("contacts")
    Call<List<Contact>> getContacts();

    @POST("contacts")
    Call<Void> addContacts(@Body Contact contact);

    @GET("contacts/{id}")
    Call<Contact> getContact(@Path("id") String id);

    @PUT("contacts/{id}")
    Call<Contact> editContact(@Path("id") String id);

    @DELETE("contacts/{id}")
    Call<Contact> deleteContact(@Path("id") String id);

    @POST("Users/Login")
    Call<String> login(@Body String userNamer, String password);

}
