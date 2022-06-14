package com.example.go_chat_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.go_chat_android.entities.Contact;

public class AddContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();

        contactDao = db.contactDao();

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            EditText username = findViewById(R.id.usernameField);
            EditText server = findViewById(R.id.serverField);
            Contact contact = new Contact(username.getText().toString(), server.getText().toString());
            contactDao.insert(contact);
            finish();
        });
    }
}
