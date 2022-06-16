package com.example.go_chat_android;

import android.app.Application;
import android.content.Context;

import com.example.go_chat_android.entities.Contact;

import java.util.List;

public class MyApplication extends Application {
    public static Context context;
    public static String token;
    public static List<Contact> contactList;
    public static String username;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
