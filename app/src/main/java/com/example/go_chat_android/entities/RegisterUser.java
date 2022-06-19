package com.example.go_chat_android.entities;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser {
    private String Username;
    private String Password;
    private String Email;
    private String NickName;
    private String Photo;
    private List<Contact> Contacts;
    private String Connection;

    public RegisterUser(String Username, String Password, String NickName, String Email, String Photo,Contact Contacts, String Connection) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.NickName = NickName;
        this.Photo = Photo;
        this.Connection = Connection;
        this.Contacts = new ArrayList<>();
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
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
