package com.example.go_chat_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.go_chat_android.R;
import com.example.go_chat_android.entities.Message;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    LayoutInflater inflater;

    public MessageAdapter(Context ctx, ArrayList<Message> userArrayList) {
        super(ctx, R.layout.right_message, userArrayList);
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Message message = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.right_message, parent, false);
        }

        TextView tvContent = convertView.findViewById(R.id.tvContent);
        TextView tvDate = convertView.findViewById(R.id.tvDate);

        tvContent.setText(message.getContent());
        tvDate.setText(message.getCreated());

        return convertView;
    }

}
