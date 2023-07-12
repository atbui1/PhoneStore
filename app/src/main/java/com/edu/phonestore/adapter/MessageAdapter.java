package com.edu.phonestore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.phonestore.R;
import com.edu.phonestore.click.IClickMessage;
import com.edu.phonestore.click.IClickProduct;
import com.edu.phonestore.model.Product;
import com.edu.phonestore.room.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private IClickMessage iClickMessage;


    public MessageAdapter(List<User> users, Context context, IClickMessage iClickMessage) {
        this.users = users;
        this.context = context;
        this.iClickMessage = iClickMessage;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row_message, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        if (user == null) {
            return;
        }

        holder.txtName.setText(users.get(position).getUsername());
        holder.txtMessage.setText("last message pos: " + position);
        holder.txtMessageTime.setText(position + 1 + " min");

        if (position % 2 == 0) {
            holder.txtMessageStatus.setText("New");
            holder.txtMessageStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            holder.txtMessageStatus.setText("seen");
            holder.txtMessageStatus.setTextColor(ContextCompat.getColor(context, R.color.sick_green));
        }
        if (users.get(position).getAvatar() != null) {
            System.out.println("url123: " + users.get(position).getAvatar());
            Picasso.with(context).load(users.get(position).getAvatar())
                    .placeholder(R.drawable.img_a)
                    .error(R.drawable.img_b)
//                        .error(R.drawable.img_default)
                    .into(holder.imgAvatar);
        } else {
            holder.imgAvatar.setImageResource(R.mipmap.logo_shop);
        }

        /** event click */
        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickMessage.clickMessage(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMessage, txtMessageTime, txtMessageStatus;
        ImageView imgAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtMessage = (TextView) itemView.findViewById(R.id.txt_message);
            txtMessageTime = (TextView) itemView.findViewById(R.id.txt_message_time);
            txtMessageStatus = (TextView) itemView.findViewById(R.id.txt_message_status);
            imgAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
        }
    }
}
