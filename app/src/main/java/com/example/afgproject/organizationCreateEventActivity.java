package com.example.afgproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//nav imports
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
//nav imports
public class organizationCreateEventActivity extends AppCompatActivity {

    //nav
    DrawerLayout eventDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    // /nav


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_create_event);

        //fromHours dropdown
        Spinner fromHoursDropdownSpinner=findViewById(R.id.fromHoursDropdown);
        ArrayAdapter<CharSequence> fromHoursAdapter =ArrayAdapter.createFromResource(this, R.array.hoursDropdown, android.R.layout.simple_spinner_item);
        fromHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromHoursDropdownSpinner.setAdapter(fromHoursAdapter);

        //fromMinutes dropdown
        Spinner fromMinutesDropdownSpinner=findViewById(R.id.fromMinutesDropdown);
        ArrayAdapter<CharSequence> fromMinutesAdapter =ArrayAdapter.createFromResource(this, R.array.minutesDropdown, android.R.layout.simple_spinner_item);
        fromMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromMinutesDropdownSpinner.setAdapter(fromMinutesAdapter);

        //fromAmPm dropdown
        Spinner fromAmPmDropdownSpinner=findViewById(R.id.fromAmPmDropdown);
        ArrayAdapter<CharSequence> fromAmPmAdapter =ArrayAdapter.createFromResource(this, R.array.AmPmDropdown, android.R.layout.simple_spinner_item);
        fromAmPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromAmPmDropdownSpinner.setAdapter(fromAmPmAdapter);

        //toHours dropdown
        Spinner toHoursDropdownSpinner=findViewById(R.id.toHoursDropdown);
        ArrayAdapter<CharSequence> toHoursAdapter =ArrayAdapter.createFromResource(this, R.array.hoursDropdown, android.R.layout.simple_spinner_item);
        toHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toHoursDropdownSpinner.setAdapter(toHoursAdapter);

        //toMinutes dropdown
        Spinner toMinutesDropdownSpinner=findViewById(R.id.toMinutesDropdown);
        ArrayAdapter<CharSequence> toMinutesAdapter =ArrayAdapter.createFromResource(this, R.array.minutesDropdown, android.R.layout.simple_spinner_item);
        toMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toMinutesDropdownSpinner.setAdapter(toMinutesAdapter);

        //toAmPm dropdown
        Spinner toAmPmDropdownSpinner=findViewById(R.id.toAmPmDropdown);
        ArrayAdapter<CharSequence> toAmPmAdapter =ArrayAdapter.createFromResource(this, R.array.AmPmDropdown, android.R.layout.simple_spinner_item);
        toAmPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toAmPmDropdownSpinner.setAdapter(toAmPmAdapter);

       //skills required dropdown
        Spinner skillsRequiredDropdownSpinner=findViewById(R.id.skillsRequiredDropdown);
        ArrayAdapter<CharSequence> skillsRequiredAdapter =ArrayAdapter.createFromResource(this, R.array.skillsRequiredDropdown, android.R.layout.simple_spinner_item);
        skillsRequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        skillsRequiredDropdownSpinner.setAdapter(skillsRequiredAdapter);

        //interest fields dropdown
        Spinner interestFieldsDropdownSpinner=findViewById(R.id.interestFieldsDropdown);
        ArrayAdapter<CharSequence> interestFieldsAdapter =ArrayAdapter.createFromResource(this, R.array.interestFieldsDropdown, android.R.layout.simple_spinner_item);
        interestFieldsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
       interestFieldsDropdownSpinner.setAdapter(interestFieldsAdapter);

        //event type dropdown
        Spinner eventTypeDropdownSpinner=findViewById(R.id.eventTypeDropdown);
        ArrayAdapter<CharSequence> eventTypeAdapter =ArrayAdapter.createFromResource(this, R.array.eventTypeDropdown, android.R.layout.simple_spinner_item);
       eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        eventTypeDropdownSpinner.setAdapter(eventTypeAdapter);

        //upload button attempt
        //below video uploads file to firebase storage?
        //https://www.youtube.com/watch?v=j11O6DvkePg
        // /upload button attempt



//nav
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_home:

                        Intent homeIntent = new Intent(organizationCreateEventActivity.this, MainActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(homeIntent);
                        break;

                    case  R.id.nav_profileDrawer:

                        Intent profileIntent = new Intent(organizationCreateEventActivity.this, organizationProfile.class); //changed both o.c.e.a's from main activity
                        startActivity(profileIntent);
                        break;


                    case  R.id.nav_eventDrawer:

                        Intent eventIntent = new Intent(organizationCreateEventActivity.this, organizationCreateEventActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(eventIntent);
                        break;

                    case  R.id.nav_volunteerSearchDrawer:

                        Intent searchIntent = new Intent(organizationCreateEventActivity.this, volunteerSearch.class);
                        startActivity(searchIntent);
                        break;


//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }
    public void setUpToolbar() {
        eventDrawerLayout = findViewById(R.id.eventDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, eventDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        eventDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

// /nav
    }




}