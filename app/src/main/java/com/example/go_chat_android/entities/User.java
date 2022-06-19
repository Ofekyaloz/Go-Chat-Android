package com.example.go_chat_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "usersTable")
public class User {
    @NonNull
    @PrimaryKey
    private String Username;
    private String Password;
    private String Email;
    private String NickName;
    private String Photo;
    private List<Contact> Contacts;
    private String Connection;

    public User(String Username, String Password, String NickName, String Email, String Photo, List<Contact> Contacts, String Connection) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.NickName = NickName;
        this.Photo = Photo;
        this.Connection = Connection;
        this.Contacts = new ArrayList<>();
    }

    public String getUserName() {
        return Username;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getNickName() {
        return NickName;
    }

    public String getPhoto() {
        return Photo;
    }

    public List<Contact> getContacts() {
        return Contacts;
    }

    public String getConnection() {
        return Connection;
    }
}
