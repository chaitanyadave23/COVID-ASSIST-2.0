package com.example.covidassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNewFeedActivity extends AppCompatActivity {
    EditText item_name,item_quantity, item_desc;
    Button add_new_feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_feed);

        item_name = findViewById(R.id.edt_item_name);
        item_quantity = findViewById(R.id.edt_item_quantity);
        item_desc = findViewById(R.id.edt_item_desc);

        add_new_feed = findViewById(R.id.btn_add_feed);

        add_new_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("user_id", FirebaseAuth.getInstance().getUid());
                map.put("item_name", item_name.getText().toString());
                map.put("item_quantity", item_quantity.getText().toString());
                map.put("item_desc", item_desc.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("UserFeed").push().setValue(map);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


                Toast. makeText(getApplicationContext(),"Feed Added Successfully",Toast. LENGTH_SHORT);
            }
        });

    }
}

