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

import com.example.covidassist.Adapters.MyChatAdapter;

import com.example.covidassist.Model.Chat;
import com.example.covidassist.Model.User;

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
import java.util.HashSet;
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
    List<User> mUsers;
    HashSet<String> chatUserList;
    MyChatAdapter myChatAdapter;


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
        chatUserList = new HashSet<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getSender().equals(fuser.getUid())) chatUserList.add(chat.getReceiver());
                    if(chat.getReceiver().equals(fuser.getUid())) chatUserList.add(chat.getSender());
                }

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void readChats(){
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);

                    for(String id: chatUserList){
                        if(user.getUserid().equals(id)){
                                mUsers.add(user);
                        }
                    }
                }
                 myChatAdapter = new MyChatAdapter(getActivity(), mUsers);
                 recyclerView.setAdapter(myChatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
