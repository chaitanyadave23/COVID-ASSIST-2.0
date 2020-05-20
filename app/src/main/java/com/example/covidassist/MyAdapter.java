package com.example.covidassist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<feed> feeds;

    public MyAdapter(Context mContext , ArrayList<feed> f)
    {
        this.mContext = mContext;
        this.feeds = f;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cardview,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_name.setText(feeds.get(position).getItem_name());
        holder.item_desc.setText(feeds.get(position).getItem_desc());
        holder.item_quantity.setText(feeds.get(position).getItem_quantity());

        final feed userfeed = feeds.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,MessageActivity.class);
                intent.putExtra("user_id",userfeed.getItem_name());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_name,item_desc,item_quantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);
        }
    }
}
