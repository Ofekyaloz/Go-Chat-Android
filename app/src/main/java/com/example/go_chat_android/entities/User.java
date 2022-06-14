package com.example.go_chat_android.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String email;
    private String nickName;
    private String photo;
    private List<Contact> contacts;
    private String connection;

    public User(String userName, String password, String email, String nickName, String photo, String connection) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.photo = photo;
        this.connection = connection;
        this.contacts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getConnection() {
        return connection;
    }
}
