package com.example.go_chat_android.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_chat_android.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM usersTable")
    List<User> index();

    @Query("SELECT * FROM usersTable WHERE username = :username")
    User get(String username);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}