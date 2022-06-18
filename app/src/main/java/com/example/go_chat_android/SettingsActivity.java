package com.example.go_chat_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText etInput = findViewById(R.id.setBaseUrl);
        Button btnSet = findViewById(R.id.btnSet);
        btnSet.setOnClickListener(v -> {
            String text = etInput.getText().toString();
            if (text.length() != 0) {

            }
        });

    }
}