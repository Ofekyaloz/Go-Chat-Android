package com.example.go_chat_android.entities;

import java.util.List;

public class Contact {
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastdate;
    private List<Message> Messages;
    private User user;
    private String userid;

    public Contact(String name, String server, String last, String lastdate, User user, String userid) {
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.user = user;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public List<Message> getMessages() {
        return Messages;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public void setMessages(List<Message> messages) {
        Messages = messages;
    }
}
