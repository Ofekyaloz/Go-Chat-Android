package com.example.go_chat_android.entities;

import com.example.go_chat_android.Contact;

public class Message {
    private int id;
    private String content;
    private String created;
    private Boolean sent;
    private Contact contact;

    public Message(String content, String created, Boolean sent, Contact contact) {
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
