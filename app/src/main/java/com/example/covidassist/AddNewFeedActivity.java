package com.example.covidassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidassist.Model.feed;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddNewFeedActivity extends AppCompatActivity {
    EditText item_name,item_quantity, item_desc;
    Button add_new_feed;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_feed);

        toolBar = findViewById(R.id.toolbar);
        toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        item_name = findViewById(R.id.edt_item_name);
        item_quantity = findViewById(R.id.edt_item_quantity);
        item_desc = findViewById(R.id.edt_item_desc);

        add_new_feed = findViewById(R.id.btn_add_feed);

        Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_LONG);


        add_new_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = FirebaseAuth.getInstance().getUid();
                String itemName = item_name.getText().toString();
                String itemQuantity = item_quantity.getText().toString();
                String desc = item_desc.getText().toString();

                if(itemName.length()==0 || itemQuantity.length()==0 || desc.toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    feed userFeed = new feed(itemName, itemQuantity, desc, user_id);

                    db.collection("UserFeed").add(userFeed)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Toast. makeText(getApplicationContext(),"New feed added successfully",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast. makeText(getApplicationContext(),"Error in adding to db",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }
}

