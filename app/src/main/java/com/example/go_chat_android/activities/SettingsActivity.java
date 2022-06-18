package com.example.go_chat_android.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText etInput = findViewById(R.id.setBaseUrl);
        etInput.setHint(MyApplication.BaseUrl);
        Button btnSet = findViewById(R.id.btnSet);
        btnSet.setOnClickListener(v -> {
            String text = etInput.getText().toString();
            if (text.length() != 0) {
                MyApplication.BaseUrl = text;
                finish();
            }
        });

    }
}