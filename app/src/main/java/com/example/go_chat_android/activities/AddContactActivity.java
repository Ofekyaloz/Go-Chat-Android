package com.example.go_chat_android.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.daos.ContactDao;
import com.example.go_chat_android.databinding.ActivityAddContactBinding;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.Invitation;
import com.example.go_chat_android.entities.contactFields;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;
    private ActivityAddContactBinding addContactBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContactBinding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(addContactBinding.getRoot());
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();

        contactDao = db.contactDao();

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            String contactName = addContactBinding.contactNameField.getText().toString();
            String server = addContactBinding.serverField.getText().toString();
            String contactNickname = addContactBinding.contactNicknameField.getText().toString();
            String token = MyApplication.token;
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(MyApplication.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            webServiceApi = retrofit.create(WebServiceApi.class);
            new Thread(() -> {
                contactFields contactFields = new contactFields(contactName, contactNickname, server);
                Call<Void> call = webServiceApi.addContact(contactFields, "Bearer " + token);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Contact contact = new Contact(contactName, server);
                            contact.setUserId(MyApplication.username);
                            contactDao.insert(contact);
                            finish();
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }).start();

            new Thread(() -> {
                Invitation invitation = new Invitation(MyApplication.username, contactName, "");
                Call<Void> call = webServiceApi.invitations(invitation);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {

                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }).start();
        });
    }
}
