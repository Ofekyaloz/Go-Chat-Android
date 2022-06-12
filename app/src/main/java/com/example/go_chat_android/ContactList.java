package com.example.go_chat_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.go_chat_android.adapters.ContactListAdapter;
import com.example.go_chat_android.databinding.ActivityContactsBinding;
import com.example.go_chat_android.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    final private int[] profilePictures = {
            R.drawable.icon_user_default, R.drawable.icon_user_default, R.drawable.icon_user_default,
            R.drawable.icon_user_default, R.drawable.icon_user_default, R.drawable.icon_user_default
    };

    final private String[] userNames = {
            "Blue User", "Golden User", "Green User", "Red User", "Lightblue User", "Pink User"
    };

    final private String[] lastMassages = {
            "Hi, how are you?", "24K Magic", "I'm GREEN!", "Red is my name", "wasap :)", "Yo!"
    };

    final private String[] times = {
            "12:00", "00:30", "3:23", "8:59", "14:52", "12:23"
    };

    private ActivityContactsBinding contactsBinding;
    private AppDB db;
    private ContactDao contactDao;

    ListView listView;
    ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsBinding = ActivityContactsBinding.inflate(getLayoutInflater());
        setContentView(contactsBinding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();

        contactDao = db.contactDao();

        FloatingActionButton btnAdd = findViewById(R.id.btnAddContact);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < profilePictures.length; i++) {
            Contact aContact = new Contact(userNames[i], "", lastMassages[i], times[i]);
            contacts.add(aContact);
        }

        listView = findViewById(R.id.list_view);
        adapter = new ContactListAdapter(getApplicationContext(), contacts);

        listView.setAdapter(adapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);

                intent.putExtra("userName", userNames[i]);
                intent.putExtra("profilePicture", profilePictures[i]);
                intent.putExtra("lastMassage", lastMassages[i]);
                intent.putExtra("time", times[i]);

                startActivity(intent);
            }
        });

    }
}