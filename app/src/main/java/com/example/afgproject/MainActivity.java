package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
//import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //location//
    private TextView allowLocation;
    private Button locationButton;
    private LocationRequest locationRequest;
    // /location//
    //nav
    DrawerLayout mainDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    // /nav


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//shared preferences


// /shared preferences


        //location
        allowLocation = findViewById(R.id.allowLocationPrompt);
        locationButton = findViewById(R.id.allowLocationButton);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
// /location
        //nav
        setUpToolbar();

        navigationView = (NavigationView)

                findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        break;

                    case R.id.nav_profileDrawer:

                        Intent profileIntent = new Intent(MainActivity.this, organizationProfile.class);
                        startActivity(profileIntent);
                        break;


                    case R.id.nav_eventDrawer:

                        Intent eventIntent = new Intent(MainActivity.this, organizationCreateEventActivity.class);
                        startActivity(eventIntent);
                        break;

                    case R.id.nav_volunteerSearchDrawer:

                        Intent searchIntent = new Intent(MainActivity.this, volunteerSearch.class);
                        startActivity(searchIntent);
                        break;

                    case R.id.nav_main2Drawer:

                        Intent main2Intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(main2Intent);
                        break;


                    case R.id.nav_main3Drawer:

                        Intent main3Intent = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(main3Intent);
                        break;


//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case R.id.nav_share: {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                    }
                    break;
                }
                return false;
            }});
                // /nav

                //location
                locationButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                                if (isGPSEnabled()) {

                                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                                @Override
                                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                                    super.onLocationResult(locationResult);

                                                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                                            .removeLocationUpdates(this);
                                                    if (locationResult != null && locationResult.getLocations().size() > 0) {

                                                        int index = locationResult.getLocations().size() - 1;
                                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                                        allowLocation.setText("Latitude: " + latitude + "  " + "Longitude: " + longitude);
                                                    }
                                                }
                                            }, Looper.getMainLooper());

                                } else {
                                    turnOnGPS();
                                }
                            } else {
                                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                            }

                            // /location

                        }
                    }
                });
            }

    public void setUpToolbar() {
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mainDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
// /nav

    //location

    private void turnOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MainActivity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MainActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;


        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }
}