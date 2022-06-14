package com.example.go_chat_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.go_chat_android.R;
import com.example.go_chat_android.entities.Contact;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {
    LayoutInflater inflater;

    public ContactListAdapter(Context ctx, List<Contact> userArrayList) {
        super(ctx, R.layout.custom_list_item, userArrayList);
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_image);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView lastMsg = convertView.findViewById(R.id.last_massage);
        TextView time = convertView.findViewById(R.id.time);

        imageView.setImageResource(R.drawable.icon_user_default);
        userName.setText(contact.getName());
        lastMsg.setText(contact.getLast());
        time.setText(contact.getLastdate());

        return convertView;
    }
}
