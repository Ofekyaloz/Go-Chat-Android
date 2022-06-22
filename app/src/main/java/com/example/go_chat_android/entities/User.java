package com.example.go_chat_android.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usersTable")
public class User {
    @NonNull
    @PrimaryKey
    private String Username;
    private String NickName;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public User(String Username, String NickName, byte[] image) {
        this.Username = Username;
        this.NickName = NickName;
        this.image = image;
    }

    public String getUsername() {
        return Username;
    }

    public String getNickName() {
        return NickName;
    }

    public byte[] getImage() {
        return image;
    }

}
