package com.example.go_chat_android.lists;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.activities.AddContactActivity;
import com.example.go_chat_android.adapters.ContactAdapter;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactList extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contactList;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;
    private ListView listView;
    private ContactAdapter adapter;

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

        contactList = contactDao.getContacts(MyApplication.username);

        listView = findViewById(R.id.list_view);
        adapter = new ContactAdapter(getApplicationContext(), contactList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        new Thread(() -> {
            String token = MyApplication.token;
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(MyApplication.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            webServiceApi = retrofit.create(WebServiceApi.class);
            Call<List<Contact>> call = webServiceApi.getContacts("Bearer " + token);
            call.enqueue(new Callback<List<Contact>>() {
                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                    if (response.isSuccessful()) {
//                        contactList = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {

                }
            });


        }).start();

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
        contactList.addAll(contactDao.getContacts(MyApplication.username));
        adapter.notifyDataSetChanged();
    }
}