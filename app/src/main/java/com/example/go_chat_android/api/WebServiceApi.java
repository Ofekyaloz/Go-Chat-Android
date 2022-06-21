package com.example.go_chat_android.api;

import com.example.go_chat_android.entities.ContactClass;
import com.example.go_chat_android.entities.Content;
import com.example.go_chat_android.entities.Invitation;
import com.example.go_chat_android.entities.LoginFields;
import com.example.go_chat_android.entities.MessageClass;
import com.example.go_chat_android.entities.RegisterUser;
import com.example.go_chat_android.entities.Transfer;
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
    Call<List<ContactClass>> getContacts(@Header("Authorization") String token);

    @POST("Contacts")
    Call<Void> addContact(@Body contactFields contact, @Header("Authorization") String token);

    @POST("Users/Login")
    Call<String> login(@Body LoginFields loginFields);

    @POST("Users/Register")
    Call<String> register(@Body RegisterUser user);

    @GET("Contacts/{id}/Messages/")
    Call<List<MessageClass>> getMessages(@Path("id") String id, @Header("Authorization") String token);

    @POST("Contacts/{id}/Messages/")
    Call<Void> addMessage(@Path("id") String id, @Body Content content, @Header("Authorization") String token);

    @POST("transfer")
    Call<Void> transfer(@Body Transfer transfer);

    @POST("invitations")
    Call<Void> invitations(@Body Invitation invitation);
}
