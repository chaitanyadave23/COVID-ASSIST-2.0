package com.example.covidassist.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidassist.AddNewFeedActivity;
import com.example.covidassist.Adapters.UserFeedAdapter;
import com.example.covidassist.R;
import com.example.covidassist.Model.feed;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.view.View.OnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFeedFragment extends Fragment implements OnClickListener{

    public UserFeedFragment() {
        // Required empty public constructor
    }
    FloatingActionButton add;
    FirebaseUser fuser;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<feed> list;
    UserFeedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fuser = FirebaseAuth.getInstance().getCurrentUser();;
        //String fuid = fuser.getUid();

        add = getView().findViewById(R.id.floatingActionAdd);
        add.setOnClickListener((OnClickListener) this);

        recyclerView = (RecyclerView) getView().findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<feed>();
        reference = FirebaseDatabase.getInstance().getReference().child("UserFeed");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear(); //to clear the data in the list once it is updated otherwise the list will not clear and all the data will be added again
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    feed f = dataSnapshot1.getValue(feed.class);
                    final String fuid = fuser.getUid();
                    if(!f.getUser_id().equals(fuid)) list.add(f);

                }

                adapter = new UserFeedAdapter(getActivity(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddNewFeedActivity.class);
        startActivity(intent);
    }



}
