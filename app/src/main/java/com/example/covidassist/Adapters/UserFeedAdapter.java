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
import com.example.covidassist.R;
import com.example.covidassist.Model.feed;

import java.util.List;

public class UserFeedAdapter extends RecyclerView.Adapter<UserFeedAdapter.MyViewHolder> {

    private Context mContext;
    private List<feed> feeds;

    public UserFeedAdapter(Context mContext, List<feed> feeds) {
        this.mContext = mContext;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.item_name.setText(feeds.get(position).getItem_name());
        holder.item_desc.setText(feeds.get(position).getItem_desc());
        holder.item_quantity.setText(feeds.get(position).getItem_quantity());
//        holder.user_id.setText(feeds.get(position).getUser_id());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("user_id",feeds.get(position).getUser_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_name,item_desc,item_quantity,user_id;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);
            //user_id = (TextView) itemView.findViewById(R.id.user_id);
        }
    }
}
