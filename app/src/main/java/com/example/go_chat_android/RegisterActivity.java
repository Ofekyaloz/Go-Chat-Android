package com.example.go_chat_android;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_chat_android.databinding.ActivityRegisterBinding;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());


        registerBinding.btnRegister.setOnClickListener(v -> {
            String password = registerBinding.etRegisterPassword.getText().toString();
            TextView tvError = registerBinding.tvRegisterError;
            tvError.setText("");
//            String regEx = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*)[^\\s]{8,}$";

            if (!password.equals(registerBinding.etRegisterAgain.getText().toString())) {
                tvError.append("The passwords are different!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if (password.length() < 8) {
                tvError.append("The password must be 8 characters or longer!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[0-9].*", password)) {
                tvError.append("The password must contain at least 1 numeric character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[a-z].*", password)) {
                tvError.append("The password must contain at least 1 n lowercase character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[A-Z].*", password)) {
                tvError.append("The password must contain at least 1 uppercase character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String email = registerBinding.etRegisterEmail.getText().toString();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvError.append("Invalid email!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String username = registerBinding.etRegisterUsername.getText().toString();
            if (!Pattern.matches("[A-Za-z0-9]{4,30}$", username)) {
                tvError.append("Invalid username!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String nickname = registerBinding.etRegisterNickname.getText().toString();
            if (!Pattern.matches("[A-Za-z0-9]{4,30}$", nickname)) {
                tvError.append("Invalid nickname!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            tvError.setVisibility(View.INVISIBLE);

        });

        registerBinding.btnAddImage.setOnClickListener(v -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });
    }
}