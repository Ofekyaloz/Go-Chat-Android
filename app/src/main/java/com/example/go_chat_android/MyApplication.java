package com.example.go_chat_android;

import android.app.Application;
import android.content.Context;

import com.example.go_chat_android.daos.UserDao;
import com.example.go_chat_android.entities.Contact;

import java.util.List;

public class MyApplication extends Application {
    public static Context context;
    public static String BaseUrl;
    public static String token;
    public static String username;
    public static String friendBaseurl;
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        BaseUrl = "http://10.0.2.2:7265/api/";
    }
}
