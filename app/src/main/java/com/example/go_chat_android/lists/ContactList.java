package com.example.go_chat_android.lists;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.go_chat_android.activities.AddContactActivity;
import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.R;
import com.example.go_chat_android.adapters.ContactAdapter;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {

    final private int[] profilePictures = {
            R.drawable.icon_user_default, R.drawable.icon_user_default, R.drawable.icon_user_default,
            R.drawable.icon_user_default, R.drawable.icon_user_default, R.drawable.icon_user_default
    };

    final private String[] userNames = {
            "Blue User", "Golden User", "Green User", "Red User", "Lightblue User", "Pink User"
    };

    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contactList;

    ListView listView;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();

        contactDao = db.contactDao();

        FloatingActionButton btnAdd = findViewById(R.id.btnAddContact);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < profilePictures.length; i++) {
            Contact aContact = new Contact(userNames[i], "1.1.1.1");
            contacts.add(aContact);
        }

        contactList = new ArrayList<>();

        listView = findViewById(R.id.list_view);
        adapter = new ContactAdapter(getApplicationContext(), contactList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MessageList.class);
                intent.putExtra("contactName", contactList.get(i).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList.clear();
        contactList.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}