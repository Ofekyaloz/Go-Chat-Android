package com.example.go_chat_android.lists;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.activities.AddContactActivity;
import com.example.go_chat_android.adapters.ContactAdapter;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.daos.UserDao;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.ContactClass;
import com.example.go_chat_android.entities.Message;
import com.example.go_chat_android.entities.MessageClass;
import com.example.go_chat_android.entities.User;
import com.example.go_chat_android.viewmodels.SampleViewModel;
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
    private UserDao userDao;
    private List<Contact> contactList;
    private List<User> userList;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;
    private ListView listView;
    private ContactAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private String token;

    private void getContacts() {
        new Thread(() -> {
            Call<List<ContactClass>> call = webServiceApi.getContacts("Bearer " + token);
            call.enqueue(new Callback<List<ContactClass>>() {
                @Override
                public void onResponse(Call<List<ContactClass>> call, Response<List<ContactClass>> response) {
                    if (response.isSuccessful()) {
                        for (Contact c : contactList) {
                            contactDao.delete(c);
                        }
                        List<ContactClass> List = response.body();
                        for (ContactClass c : List) {
                            Contact contact = new Contact(c.getId(), c.getName(), c.getServer(), c.getLast(), c.getLastdate());
                            contact.setUserId(MyApplication.username);
                            contactDao.insert(contact);
                            contactList.add(contact);

                        }
                        onResume();
                    }
                }

                @Override
                public void onFailure(Call<List<ContactClass>> call, Throwable t) {
                }
            });
        }).start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB").allowMainThreadQueries().build();
        userDao = db.userDao();

        ImageView ivUser = findViewById(R.id.ivUserPic);
        TextView tvUserNickname = findViewById(R.id.tvUserNickname);

        User user = userDao.get(MyApplication.username);
        if (user != null && user.getImage() != null) {
            byte[] byteArray = user.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ivUser.setImageBitmap(Bitmap.createScaledBitmap(bmp, 80, 80, false));
        } else {
            ivUser.setImageResource(R.drawable.icon_user_default);
        }

        if (user != null && user.getNickName() != null) {
            tvUserNickname.setText(user.getNickName());
        } else {
            tvUserNickname.setText(MyApplication.username);
        }

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();

        MyApplication.userDao = db.userDao();
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

        token = MyApplication.token;
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);

       getContacts();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MessageList.class);
                intent.putExtra("contactName", contactList.get(i).getName());
                intent.putExtra("url", contactList.get(i).getServer());
                startActivity(intent);
            }
        });

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.contactsRefreshLayout);
        swipeContainer.setOnRefreshListener(() ->
                new Thread(() -> {
                    Call<List<ContactClass>> call = webServiceApi.getContacts("Bearer " + token);
                    call.enqueue(new Callback<List<ContactClass>>() {
                        @Override
                        public void onResponse(Call<List<ContactClass>> call, Response<List<ContactClass>> response) {
                            if (response.isSuccessful()) {
                                for (Contact c : contactList) {
                                    contactDao.delete(c);
                                }
                                List<ContactClass> List = response.body();
                                for (ContactClass c : List) {
                                    Contact contact = new Contact(c.getId(), c.getName(), c.getServer(), c.getLast(), c.getLastdate());
                                    contact.setUserId(MyApplication.username);
                                    contactDao.insert(contact);
                                    contactList.add(contact);

                                }
                                swipeContainer.setRefreshing(false);
                                onResume();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ContactClass>> call, Throwable t) {
                            swipeContainer.setRefreshing(false);
                        }
                    });
                }).start());
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList.clear();
        contactList.addAll(contactDao.getContacts(MyApplication.username));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyData"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getContacts();
        }
    };
}