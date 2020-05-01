package com.example.covidassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserFeedActivity extends AppCompatActivity {
    private TextView title, name, age;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        title = (TextView) findViewById(R.id.Title);
        age = (TextView) findViewById(R.id.age);
        name = (TextView) findViewById(R.id.name);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("title", title.getText().toString());
                map.put("name", name.getText().toString());
                map.put("title", age.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Student").setValue(map);



            }
        });


    }

}


