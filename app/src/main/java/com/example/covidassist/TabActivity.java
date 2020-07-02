package com.example.covidassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;



import com.example.covidassist.Adapters.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabUserFeed;
    TabItem tabChats;
    TabItem tabMyFeed;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            /*public double getlatitude(Location location) {
                return location.getLatitude();
            }

            public double getlongitude(Location location) {
                return location.getLongitude();
            }*/

            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Log.i("msg", "google it");
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Covid Assist");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        tabChats = findViewById(R.id.tabChats);
        tabUserFeed = findViewById(R.id.tabUserFeed);
        tabMyFeed = findViewById(R.id.tabMyFeed);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //getting the location and its permissions











        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
