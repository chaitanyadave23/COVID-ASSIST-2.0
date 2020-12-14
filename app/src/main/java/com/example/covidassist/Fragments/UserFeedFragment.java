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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import android.view.View.OnClickListener;
import android.widget.TextView;


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

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        add = getView().findViewById(R.id.floatingActionAdd);
        add.setOnClickListener((OnClickListener) this);

        recyclerView = (RecyclerView) getView().findViewById(R.id.myRecycler);

        Query query = FirebaseFirestore.getInstance().collection("UserFeed");

        FirestoreRecyclerOptions<feed> options = new FirestoreRecyclerOptions.Builder<feed>().setQuery(query, feed.class).build();



        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<feed, FeedViewHolder>(options) {
            @NonNull
            @Override
            public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getParentFragment().getContext()).inflate(R.layout.cardview, parent, false);
                return new FeedViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull feed model) {
                holder.item_name.setText(model.getItem_name());
                holder.item_desc.setText(model.getItem_desc());
                holder.item_quantity.setText(model.getItem_quantity());
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddNewFeedActivity.class);
        startActivity(intent);
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

    /*@Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }*/
}
