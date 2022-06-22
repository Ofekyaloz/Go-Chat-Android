package com.example.go_chat_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usersTable")
public class User {
    @NonNull
    @PrimaryKey
    private String Username;
    private String NickName;
    private String Photo;

    public User(String Username, String NickName, String Photo) {
        this.Username = Username;
        this.NickName = NickName;
        this.Photo = Photo;
    }

    public String getUsername() {
        return Username;
    }

    public String getNickName() {
        return NickName;
    }

    public String getPhoto() {
        return Photo;
    }

}
