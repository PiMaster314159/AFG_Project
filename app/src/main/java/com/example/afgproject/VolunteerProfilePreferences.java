package com.example.afgproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Activity within which the volunteer is able to update their account
 */
public class VolunteerProfilePreferences extends AppCompatActivity {
    VolunteerSettings volunteerSettings;

    /**
     * Replace fragment with user settings, and assign onClick commands to the create account and cancel buttons
     * Create account button saves the data and brings the user to the volunteer home page
     * Cancel button does not save the data and brings the user back to the starting page
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile_preferences);
        this.volunteerSettings = new VolunteerSettings();
        getSupportFragmentManager().beginTransaction().replace(R.id.volunteer_settings_holder, volunteerSettings).commit();
        Button createAccountButton = findViewById(R.id.create_profile_button);
        createAccountButton.setOnClickListener(v -> {
            System.out.println("ok");
            try {
               if(volunteerSettings.validData()){
                   saveData();
                   Intent intent = new Intent(v.getContext(), VolunteerHome.class);
                   startActivity(intent);
               }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VolunteerHome.class);
            startActivity(intent);
        });
    }

    /**
     * Save the selected data to user preferences
     * Skills and interests are checked if they are selected, and their names are used to populate the volunteer's data
     */
    public void saveData() throws IOException {
        ArrayList<ObjectMap> interestMap = this.volunteerSettings.getInterestMap();
        ArrayList<String> selectedInterestNames = new ArrayList<>();
        for(ObjectMap interest : interestMap){
            if((boolean) interest.getValue("isSelected")){
                selectedInterestNames.add((String) interest.getValue("Widget Text"));
            }
        }
        VolunteerData.putInterests(selectedInterestNames);

        ArrayList<ObjectMap> skillMap = this.volunteerSettings.getSkillMap();
        ArrayList<String> selectedSkillNames = new ArrayList<>();
        for(ObjectMap skill : skillMap){
            if((boolean) skill.getValue("isSelected")){
                selectedSkillNames.add((String) skill.getValue("Widget Text"));
            }
        }
        System.out.println("selected skills " + selectedSkillNames);
        VolunteerData.putSkills(selectedSkillNames);
//        ArrayList<UniversalHolder> allInterests = this.volunteerSettings.getAllInterests();
//        for(UniversalHolder holder : allInterests)
//            if(((SettingsRvHolder) holder).getSelected()) {
//                selectedInterestNames.add(holder.getName());
//                System.out.println(holder.getName());
//            }
//        VolunteerData.putInterests(selectedInterestNames);
//
//        ArrayList<UniversalHolder> allSkills = this.volunteerSettings.getAllSkills();
//        ArrayList<String> selectedSkillsNames = new ArrayList<>();
//        for(UniversalHolder holder : allSkills)
//            if(((SettingsRvHolder) holder).getSelected())
//                selectedSkillsNames.add(holder.getName());
//        VolunteerData.putSkills(selectedSkillsNames);
//
//        VolunteerData.putZipCode(volunteerSettings.getZipCode());
//        VolunteerData.putMaxDistance(volunteerSettings.getMaxDistance());
    }
}