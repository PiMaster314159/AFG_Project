package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

/**
 * Opening activity of the app
 * If the user already has an account, they are redirected to the corresponding account page
 * If the user is new to the app, they are greeted with a title screen and two buttons corresponding to the account they wish to make
 */
public class StartMenu extends AppCompatActivity {

    Button volunteerButton, organizationButton;

    /**
     * Create activity
     * Get the profile type of the user and redirect user to correct page
     * If the profile type is 0, remain on page and prompt user to pick account type. Clicking buttons will allow user to create an account
     * If the account type is 1, redirect to volunteer home page
     * If the account type is 2, redirect to the organization home page
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSharedPreferences("Profile", Context.MODE_PRIVATE).edit().clear().apply();
        VolunteerData.setSharedPreferences();
//        VolunteerData.clearSharedPreferences();

        int profileType = getSharedPreferences("Profile", Context.MODE_PRIVATE).getInt("ProfileType", 0);
        switch (profileType){
            case 0: {
                System.out.println(getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE).getBoolean("onStart", false));
                SharedPreferences sharedPref = getSharedPreferences((getString(R.string.user_preferences_map)), MODE_PRIVATE);
                sharedPref.edit().putBoolean("onStart", true).apply();
                setContentView(R.layout.activity_start_menu);

                volunteerButton = findViewById(R.id.volunteer_button);
                volunteerButton.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), VolunteerCreateProfile.class);
                    startActivity(intent);
//                setContentView(R.layout.activity_organization_profile);
                });

                organizationButton = findViewById(R.id.organization_button);
                organizationButton.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), OrganizationCreateProfile.class);
                    startActivity(intent);
//                setContentView(R.layout.activity_organization_profile);
                });
                break;
            }
            case 1: {
                Intent intent = new Intent(StartMenu.this, VolunteerHome.class);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(StartMenu.this, OrganizationHome.class);
                startActivity(intent);
                break;
            }
        }
    }
}