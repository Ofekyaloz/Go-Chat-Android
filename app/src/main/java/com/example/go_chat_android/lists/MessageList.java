package com.example.go_chat_android.lists;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.adapters.MessageListAdapter;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.daos.MessageDao;
import com.example.go_chat_android.entities.Message;
import com.example.go_chat_android.entities.Transfer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageList extends AppCompatActivity {
    private AppDB db;
    private MessageDao messageDao;
    private ArrayList<Message> messageList;
    private String contactName;
    private EditText etInput;
    private Button btnSend;
    private TextView tvName;
    private RecyclerView RVMessageList;
    private MessageListAdapter adapter;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDB").allowMainThreadQueries().build();

        messageDao = db.messageDao();

        contactName = getIntent().getExtras().getString("contactName");

        tvName = findViewById(R.id.tvFriendNickname);
        tvName.setText("" + contactName + "");

        messageList = new ArrayList<>();
        messageList.add(new Message("132", "123", true,contactName));

        RVMessageList = findViewById(R.id.lvMessageList);
        adapter = new MessageListAdapter(this);
        RVMessageList.setAdapter(adapter);
        RVMessageList.setLayoutManager(new LinearLayoutManager(this));
        adapter.setMessageList(messageList);

        btnSend = findViewById(R.id.btnSend);
        etInput = findViewById(R.id.etTextInput);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
        String token = MyApplication.token;
        new Thread(() -> {
            Call<List<Message>> call = webServiceApi.getMessages(contactName, "Bearer " + token);
            call.enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                }

                @Override
                public void onFailure(Call<List<Message>> call, Throwable t) {

                }
            });
        }).start();


        btnSend.setOnClickListener(v -> {
            String content = etInput.getText().toString();
            if (etInput.length() != 0) {
                Message message = new Message(content, java.time.LocalDateTime.now().toString(), true, contactName);
                messageDao.insert(message);
                onResume();
                RVMessageList.scrollToPosition(messageList.size() - 1);
                etInput.setText("");
                new Thread(() -> {
                    Call<Void> call = webServiceApi.addMessage(contactName, content, "Bearer " + token);
                }).start();

                new Thread(() -> {
                    Transfer transfer = new Transfer(MyApplication.username, contactName, content);
                    Call<Void> call = webServiceApi.transfer(transfer);
                }).start();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        messageList.clear();
        messageList.addAll(messageDao.get(contactName));
        adapter.notifyDataSetChanged();
    }
}