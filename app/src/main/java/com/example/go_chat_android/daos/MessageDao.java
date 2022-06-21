package com.example.go_chat_android.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_chat_android.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messagesTable")
    List<Message> index();
    @Query("SELECT * FROM messagesTable WHERE contactName = :contactName AND userId = :username")
    List<Message> get(String contactName, String username);
    @Insert
    void insert(Message... messages);
    @Update
    void update(Message... messages);
    @Delete
    void delete(Message... messages);
}
