package com.example.covidassist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFeedFragment extends Fragment {

    public UserFeedFragment() {
        // Required empty public constructor
    }
    FloatingActionButton add;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<feed> list;
    MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        add = getView().findViewById(R.id.floatingActionAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNewFeedActivity.class);
                startActivity(intent);
            }
        });


        /*recyclerView = (RecyclerView) getView().findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<feed>();
        reference = FirebaseDatabase.getInstance().getReference().child("UserFeed");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    feed f = dataSnapshot1.getValue(feed.class);
                    list.add(f);
                }

                adapter = new MyAdapter(getActivity(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // Toast.makeText(UserFeedActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });*/
        return inflater.inflate(R.layout.fragment_user_feed, container, false);
    }
}
