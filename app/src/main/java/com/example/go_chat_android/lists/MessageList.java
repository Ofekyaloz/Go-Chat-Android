package com.example.go_chat_android.lists;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.R;
import com.example.go_chat_android.adapters.MessageAdapter;
import com.example.go_chat_android.daos.MessageDao;
import com.example.go_chat_android.entities.Message;

import java.util.ArrayList;

public class MessageList extends AppCompatActivity {

    private AppDB db;
    private MessageDao messageDao;
    private ArrayList<Message> messageList;

    private String contactName;
    private EditText input;
    private Button send;
    private TextView name;

    ListView listView;
    MessageAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDB").allowMainThreadQueries().build();

        messageDao = db.messageDao();

        contactName = getIntent().getExtras().getString("contactName");

        name = findViewById(R.id.tvFriendNickname);
        name.setText("" + contactName + "");

        messageList = new ArrayList<>();

        listView = findViewById(R.id.lvMessageList);
        adapter = new MessageAdapter(getApplicationContext(), messageList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        send = findViewById(R.id.btnSend);
        send.setOnClickListener(v -> {
            input = findViewById(R.id.etTextInput);
            if (input.length() != 0) {
                Message message = new Message(input.getText().toString(), java.time.LocalDateTime.now().toString(), true, contactName);
                messageDao.insert(message);
                onResume();
                input.setText("");
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