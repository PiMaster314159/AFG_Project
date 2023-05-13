package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
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

//import com.google.android.gms.location.LocationAvailability;
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

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //nav
    Button volunteerButton, organizationButton;
    // /nav


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE).getBoolean("onStart", false));
        SharedPreferences sharedPref = getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE);
        VolunteerSharedData.setSharedPreferences();
        sharedPref.edit().putBoolean("onStart", true).apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volunteerButton = findViewById(R.id.volunteer_button);
        volunteerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VolunteerPreferences.class);
                startActivity(intent);
//                setContentView(R.layout.activity_organization_profile);
            }
        });

        organizationButton = findViewById(R.id.organization_button);
        organizationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), organizationCreateProfile.class);
                startActivity(intent);
//                setContentView(R.layout.activity_organization_profile);
            }
        });

    }
}