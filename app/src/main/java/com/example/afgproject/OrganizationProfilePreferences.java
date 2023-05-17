package com.example.afgproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity within which the organization is able to update their account
 */
public class OrganizationProfilePreferences extends AppCompatActivity {

    Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_create_profile);
        saveButton = findViewById(R.id.create_profile_button);
        saveButton.setOnClickListener(v -> {
            saveData();
            Intent intent = new Intent(v.getContext(), OrganizationHome.class);
            startActivity(intent);
        });
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {
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