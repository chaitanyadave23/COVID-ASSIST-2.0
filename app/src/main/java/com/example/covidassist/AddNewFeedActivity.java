package com.example.covidassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNewFeedActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;

    EditText item_name, item_quantity, item_desc;
    Button add_new_feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_feed);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        final double longitude = getlongitude(location);
        final double latitude = getlatitude(location);


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
                map.put("longitude",longitude);
                map.put("latitude",latitude);



                //code to for manual addition of data

                /*HashMap<String, String> map = new HashMap<>();
                map.put("Handle", "Nilesh@123");
                map.put("Name", "Nilesh");
                map.put("Phno","8279815881");
                map.put("Userid", "MluNUAmes6el459mqiIniAI0bYe2");
                map.put("Latitude", "30.3165");
                map.put("Longitude", "78.0322");
*/

                FirebaseDatabase.getInstance().getReference().child("UserFeed").push().setValue(map);

                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(intent);


                Toast. makeText(getApplicationContext(),"Feed Added Successfully",Toast. LENGTH_SHORT);
            }
        });


    }

    private double getlatitude(Location location) {
        return location.getLatitude();
    }

    private double getlongitude(Location location) {
        return location.getLongitude();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
