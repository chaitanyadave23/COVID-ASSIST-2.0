package com.example.covidassist.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidassist.AddNewFeedActivity;
import com.example.covidassist.Adapters.UserFeedAdapter;
import com.example.covidassist.Model.User;
import com.example.covidassist.R;
import com.example.covidassist.Model.feed;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    DatabaseReference reference1;
    RecyclerView recyclerView;
    ArrayList<feed> list;
    UserFeedAdapter adapter;
    String longi, lati, otherid;


    //func to calculate the dist
    public static double distance(double lon1,double lat1,double lon2,double lat2)
    {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return(c * r);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        String fuid = fuser.getUid();

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
//---------------------------------------------------------------------------

                   /* reference1 = FirebaseDatabase.getInstance().getReference();

                    otherid = f.getUser_id();

                    Query query = reference1.child("Users").orderByChild("Userid").equalTo(otherid);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot user1 : dataSnapshot.getChildren()) {
                                ArrayList<String> uu = user1.getValue(User.class);
                                longi = uu.getLongi();
                                lati = uu.getLati();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
*/
//--------------------------------------------------------------------------

                   /* Double la1 = Double.parseDouble(lati);
                    Double lo1 = Double.parseDouble(longi);*/

                    //Log.i("hello", lati);
                   // String ccc=lati;
                   // Log.i("hello", ccc);
                    Double la2 = 23.0225;
                    Double lo2 = 72.5714;

                    Double la1 = 30.3165;
                    Double lo1 = 78.0322;


                    if(!f.getUser_id().equals(fuid) && distance(la1, lo1, la2, lo2) > 1000) list.add(f);

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
