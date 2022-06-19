package com.example.go_chat_android.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_chat_android.entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contactsTable")
    List<Contact> index();
    @Query("SELECT * FROM contactsTable WHERE userId = :userId")
    List<Contact> getContacts(String userId);
    @Insert
    void insert(Contact... contacts);
    @Update
    void update(Contact... contacts);
    @Delete
    void delete(Contact... contacts);
}
