package com.example.afgproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //nav
    Button volunteerButton, organizationButton;
    // /nav


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSharedPreferences("Profile", Context.MODE_PRIVATE).edit().clear().apply();
        VolunteerSharedData.setSharedPreferences();
        VolunteerSharedData.clearSharedPreferences();

        int profileType = getSharedPreferences("Profile", Context.MODE_PRIVATE).getInt("ProfileType", 0);
        switch (profileType){
            case 0: {
                System.out.println(getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE).getBoolean("onStart", false));
                SharedPreferences sharedPref = getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE);
                sharedPref.edit().putBoolean("onStart", true).apply();
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
                break;
            }
            case 1: {
                Intent intent = new Intent(MainActivity.this, ReccomendActivities.class);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(MainActivity.this, OrganizationHome.class);
                startActivity(intent);
                break;
            }
        }
    }
}
//System.out.println(getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE).getBoolean("onStart", false));
//        SharedPreferences sharedPref = getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE);
//        VolunteerSharedData.setSharedPreferences();
//        sharedPref.edit().putBoolean("onStart", true).apply();
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        volunteerButton = findViewById(R.id.volunteer_button);
//        volunteerButton.setOnClickListener(new OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent intent = new Intent(v.getContext(), VolunteerPreferences.class);
//        startActivity(intent);
////                setContentView(R.layout.activity_organization_profile);
//        }
//        });
//
//        organizationButton = findViewById(R.id.organization_button);
//        organizationButton.setOnClickListener(new OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent intent = new Intent(v.getContext(), organizationCreateProfile.class);
//        startActivity(intent);
////                setContentView(R.layout.activity_organization_profile);
//        }
//        });