package com.edu.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.phonestore.R;
import com.edu.phonestore.click.IClickChat;
import com.edu.phonestore.click.IClickMessage;
import com.edu.phonestore.model.Chat;
import com.edu.phonestore.room.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> chats;
    private Context context;
    private IClickChat iClickChat;

    public void setData(List<Chat> list) {
        this.chats = list;
        notifyDataSetChanged();
    }

    public ChatAdapter() {

    }

    public ChatAdapter(List<Chat> chats, Context context, IClickChat iClickChat) {
        this.chats = chats;
        this.context = context;
        this.iClickChat = iClickChat;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row_chat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        if (chat == null) {
            return;
        }

        holder.txtMessage.setText(chat.getMessage() + " pos: " + position);

        /** event click */
        holder.txtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickChat.clickChat(chat);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (chats != null) {
            return chats.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = (TextView) itemView.findViewById(R.id.txt_message);
        }
    }
}
