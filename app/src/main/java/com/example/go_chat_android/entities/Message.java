package com.example.go_chat_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messagesTable")
public class Message {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String created;
    private Boolean sent;

    public Message() {}

    public Message(String content, String created, Boolean sent, String contactName) {
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.contactName = contactName;
    }

    private String contactName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
