package com.example.go_chat_android.entities;

public class Invitation {
    private String from;
    private String to;
    private String server;

    public Invitation(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
}
