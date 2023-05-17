package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity within which the organization is able to create their account for the first time
 */
public class OrganizationCreateProfile extends AppCompatActivity {

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_create_profile);
        saveButton = findViewById(R.id.create_profile_button);
        saveButton.setOnClickListener(v -> {
            getSharedPreferences("Profile", Context.MODE_PRIVATE).edit().putInt("ProfileType", 2).apply();
            saveData();
            Intent intent = new Intent(v.getContext(), OrganizationHome.class);
            startActivity(intent);
        });
        OrganizationSettings organizationSettings = new OrganizationSettings();
        getSupportFragmentManager().beginTransaction().replace(R.id.volunteer_settings_holder, organizationSettings).commit();
    }

    // TODO Add saveData function such that all organization data is saved to firebase, and is pulled via a FireStore id stored in shared preferences
    private void saveData(){
        return;
    }
}