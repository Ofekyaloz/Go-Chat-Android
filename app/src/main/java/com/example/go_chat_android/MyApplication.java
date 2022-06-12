package com.example.go_chat_android;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;

//    @Override
    public void OnCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
