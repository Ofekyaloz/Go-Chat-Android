package com.example.go_chat_android.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.api.WebServiceApi;
import com.example.go_chat_android.databinding.ActivityRegisterBinding;
import com.example.go_chat_android.entities.User;
import com.example.go_chat_android.lists.ContactList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;
    private final int GALLERY_REQ_CODE = 1000;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    private Gson gson;
    @SuppressLint("SetTextI18n")
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
                tvError.setText("The passwords are different!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if (password.length() < 8) {
                tvError.setText("The password must be 8 characters or longer!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[0-9].*", password)) {
                tvError.setText("The password must contain at least 1 numeric character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[a-z].*", password)) {
                tvError.setText("The password must contain at least 1 n lowercase character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if(!Pattern.matches(".*[A-Z].*", password)) {
                tvError.setText("The password must contain at least 1 uppercase character!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String email = registerBinding.etRegisterEmail.getText().toString();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvError.setText("Invalid email!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String username = registerBinding.etRegisterUsername.getText().toString();
            if (!Pattern.matches("[A-Za-z0-9]{4,30}$", username)) {
                tvError.setText("Invalid username!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            String nickname = registerBinding.etRegisterNickname.getText().toString();
            if (!Pattern.matches("[A-Za-z0-9]{4,30}$", nickname)) {
                tvError.setText("Invalid nickname!");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            tvError.setVisibility(View.INVISIBLE);
            new Thread(() -> {
                User user = new User(username,password, nickname, email , "", null, "http:localhost:7265");
                gson = new GsonBuilder()
                        .setLenient()
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                webServiceApi = retrofit.create(WebServiceApi.class);
                Call<String> call = webServiceApi.register(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            MyApplication.token = response.body();
                            Intent intent = new Intent(getApplicationContext(), ContactList.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }).start();

        });


        registerBinding.btnAddImage.setOnClickListener(v -> {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });
    }
}