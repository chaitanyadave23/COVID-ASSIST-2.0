package com.example.covidassist.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidassist.Adapters.MyFeedAdapter;
import com.example.covidassist.Model.Chat;
import com.example.covidassist.Model.feed;
import com.example.covidassist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    public ChatFragment() {
        // Required empty public constructor
    }
    FirebaseUser fuser;
    RecyclerView recyclerView;
    DatabaseReference reference;
    List<feed> mUsers;
    List<String> chatUserList;
    MyFeedAdapter myFeedAdapter;


    public void readChats(){
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("UserFeed");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for(DataSnapshot dataSnapshot: dataSnapshot.getChildren()){
                    feed f = dataSnapshot.getValue(feed.class);

                    for(String id: chatUserList){
                        if(f.getUser_id().equals(id)){
                            if(mUsers.size()!=0){
                                for(feed ff: mUsers){
                                    if(!f.getUser_id().equals(ff.getUser_id())) mUsers.add(f);
                                }
                            }
                        }
                        else{
                            mUsers.add(f);
                        }
                    }
                }

                myFeedAdapter = new MyFeedAdapter(getActivity(), mUsers);
                recyclerView.setAdapter(myFeedAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        chatUserList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getSender().equals(fuser.getUid())) chatUserList.add(chat.getReceiver());
                    if(chat.getReceiver().equals(fuser.getUid())) chatUserList.add(chat.getSender());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            readChats();

        });
    }

}
