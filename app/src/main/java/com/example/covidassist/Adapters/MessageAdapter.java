package com.example.covidassist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidassist.Chat;
import com.example.covidassist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mchat;
//    private String imageurl;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mchat) {
        this.mContext = mContext;
        this.mchat = mchat;
//        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            return new MessageAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false));
        }
        else{
            return new MessageAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, final int position) {
        Chat chat = mchat.get(position);

        holder.show_message.setText(chat.getMessage());

//        if(imageurl.equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        }
//        else{
//            Glide.with(mContext).load(imageurl).into(holder.profile_image);

        }

    @Override
    public int getItemCount() {
        return mchat.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView show_message;
        public ImageView profile_image;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
         fuser = FirebaseAuth.getInstance().getCurrentUser();
         if(mchat.get(position).getSender().equals(fuser.getUid())){
             return MSG_TYPE_RIGHT;
         }
         else{
             return MSG_TYPE_LEFT;
         }
    }
}

