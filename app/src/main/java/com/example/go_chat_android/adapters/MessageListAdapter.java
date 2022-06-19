package com.example.go_chat_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go_chat_android.R;
import com.example.go_chat_android.entities.Message;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;
        private final TextView tvDate;

        private MessageViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> messageList;
    private MessageViewHolder left;
    private MessageViewHolder right;

    public MessageListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        left = new MessageViewHolder(mInflater.inflate(R.layout.left_message, parent, false));
        right = new MessageViewHolder(mInflater.inflate(R.layout.right_message, parent, false));
        return right;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (messageList != null) {
            final Message msg = messageList.get(position);
            if (!msg.getSent()) {
                left.tvContent.setText(msg.getContent());
                left.tvDate.setText(msg.getCreated());
                return;
            }
            holder.tvContent.setText(msg.getContent());
            holder.tvDate.setText(msg.getCreated());
        }
    }

    public void setMessageList(List<Message> list) {
        messageList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messageList != null)
            return messageList.size();
        return 0;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
}
