package com.example.go_chat_android;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.daos.MessageDao;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.Message;

@Database(entities = {Contact.class, Message.class}, version = 3)
public abstract class AppDB extends RoomDatabase{
    public abstract MessageDao messageDao();
    public abstract ContactDao contactDao();
    private static AppDB INSTANCE;

    public static AppDB getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), AppDB.class, "localDB")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
