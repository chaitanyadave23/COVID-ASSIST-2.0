package com.example.covidassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintJob;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidassist.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.jar.Attributes;

public class ProfileActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private Button signOutButton;
    private TextView NameView;
    private TextView PhoneView;
    private TextView UsernameView;
    private TextView User_feed_radiusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolBar = findViewById(R.id.toolbar);
        toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        NameView = findViewById(R.id.tv_name);
        PhoneView = findViewById(R.id.tv_phone);
        UsernameView = findViewById(R.id.tv_username);
        User_feed_radiusView = findViewById(R.id.tv_user_feed_radius);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        Source source = Source.CACHE;
        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        NameView.setText(document.getString("Name"));
                        PhoneView.setText(document.getString("Phone"));
                        UsernameView.setText(document.getString("Username"));
                        User_feed_radiusView.setText(document.getString("Userfeed_radius") + " km");

                    } else {
                        //Log.d(TAG, "No such document");
                        Toast.makeText(getBaseContext(), FirebaseAuth.getInstance().getCurrentUser().getUid() , Toast.LENGTH_LONG).show();
                    }
                } else {
                   // Log.d(TAG, "get failed with ", task.getException());
                    Toast.makeText(getBaseContext(), "get failed with ", Toast.LENGTH_LONG).show();
                }
            }
        });



        signOutButton = findViewById(R.id.btn_sign_out);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
            builder.setMessage("Do you want to Sign out ?");
            builder.setTitle("Alert !");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            }
        });
    }
}