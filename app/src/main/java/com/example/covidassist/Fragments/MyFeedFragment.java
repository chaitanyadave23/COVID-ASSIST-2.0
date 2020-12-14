package com.example.covidassist.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covidassist.Adapters.MyFeedAdapter;
import com.example.covidassist.Model.feed;
import com.example.covidassist.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeedFragment extends Fragment {

    FirebaseUser fuser;
    RecyclerView recyclerView;
    FirestoreRecyclerAdapter adapter;

    public MyFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = (RecyclerView) getView().findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();

        Query query = FirebaseFirestore.getInstance()
                .collection("UserFeed")
                .whereEqualTo("user_id", fuser.getUid());

        FirestoreRecyclerOptions<feed> options = new FirestoreRecyclerOptions.Builder<feed>().setQuery(query, feed.class).build();

        adapter = new FirestoreRecyclerAdapter<feed, MyFeedFragment.FeedViewHolder>(options) {
            @NonNull
            @Override
            public MyFeedFragment.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                return new MyFeedFragment.FeedViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyFeedFragment.FeedViewHolder holder, int position, @NonNull feed model) {
                holder.item_name.setText(model.getItem_name());
                holder.item_desc.setText(model.getItem_desc());
                holder.item_quantity.setText(model.getItem_quantity());
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }

    private class FeedViewHolder extends RecyclerView.ViewHolder {

        private TextView item_name;
        private TextView item_quantity;
        private TextView item_desc;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
