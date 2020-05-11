package com.example.covidassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<feed> feeds;

    public MyAdapter(Context c , ArrayList<feed> f)
    {
        context = c;
        feeds = f;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_name.setText(feeds.get(position).getItem_name());
        holder.item_desc.setText(feeds.get(position).getItem_desc());
        holder.item_quantity.setText(feeds.get(position).getItem_quantity());
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
