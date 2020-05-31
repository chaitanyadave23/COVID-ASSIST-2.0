package com.example.covidassist.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidassist.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeedFragment extends Fragment {

    public MyFeedFragment() {
        // Required empty public constructor
    }

    FirebaseUser fuser;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<feed> list;
    MyFeedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_feed, container, false);
    }
}
