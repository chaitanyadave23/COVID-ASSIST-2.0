package com.example.covidassist.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidassist.MessageActivity;
import com.example.covidassist.Model.User;
import com.example.covidassist.R;
import com.example.covidassist.Model.feed;

import java.util.List;

public class MyChatAdapter extends RecyclerView.Adapter<MyChatAdapter.MyViewHolder> {

    private Context mContext;
    private List<User> users;

    public MyChatAdapter(Context mContext, List<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.item_name.setText(users.get(position).getItem_name());
        holder.item_desc.setText(users.get(position).getItem_desc());
        holder.item_quantity.setText(users.get(position).getItem_quantity());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("user_id",users.get(position).getUser_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_name,item_desc,item_quantity,user_id;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
        }
    }
}
