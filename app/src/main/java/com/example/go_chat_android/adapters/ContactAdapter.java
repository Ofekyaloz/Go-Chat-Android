package com.example.go_chat_android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.go_chat_android.AppDB;
import com.example.go_chat_android.MyApplication;
import com.example.go_chat_android.R;
import com.example.go_chat_android.daos.UserDao;
import com.example.go_chat_android.entities.Contact;
import com.example.go_chat_android.entities.User;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    LayoutInflater inflater;

    public ContactAdapter(Context ctx, List<Contact> userArrayList) {
        super(ctx, R.layout.contact_list_item, userArrayList);
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_image);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView lastMsg = convertView.findViewById(R.id.last_massage);
        TextView time = convertView.findViewById(R.id.time);

        User user = MyApplication.userDao.get(contact.getName());
        if (user != null && user.getImage() != null) {
            byte[] byteArray = user.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 80, 80, false));
        }
        else {
            imageView.setImageResource(R.drawable.icon_user_default);
        }

        userName.setText(contact.getNickname());
        lastMsg.setText(contact.getLast());
        time.setText(contact.getLastdate());

        return convertView;
    }
}
