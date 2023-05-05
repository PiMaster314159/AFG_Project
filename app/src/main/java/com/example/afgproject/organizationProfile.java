package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class organizationProfile extends AppCompatActivity {

    //nav
    DrawerLayout profileDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    // /nav


    TextInputEditText organizationEmail, headEmail, organizationPhone, headPhone;
    EditText organizationName;
    String organizationNameStr, organizationEmailStr, organizationPhoneStr, headPhoneStr, headEmailStr;
    Button button;
    SharedPreferences sp;
    TextView organizationProfileTitleTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_profile);

        //shared pref
        organizationName = findViewById(R.id.organizationProfileSetupTitle);
        organizationEmail = findViewById(R.id.organizationEmailInput);
        organizationPhone = findViewById(R.id.organizationPhoneInput);
        headEmail = findViewById(R.id.headEmailInput);
        headPhone = findViewById(R.id.headPhoneInput);
        button = findViewById(R.id.saveButton);
//get sp
        SharedPreferences getsp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

// / get sp
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                organizationNameStr= String.valueOf(organizationName.getText());
                organizationEmailStr= String.valueOf(organizationEmail);
                organizationPhoneStr= String.valueOf(organizationPhone);
                headEmailStr= String.valueOf(headEmail);
                headPhoneStr= String.valueOf(headPhone);

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("organizationName", organizationNameStr);
                editor.putString("organizationEmail",organizationEmailStr);
                editor.putString("organizationPhone",organizationPhoneStr);
                editor.putString("headEmail",headEmailStr);
                editor.putString("headPhone",headPhoneStr);
                editor.commit();
                Toast.makeText(organizationProfile.this,"Information Saved.",Toast.LENGTH_LONG).show();
            }
        });

        //getting stored data
        organizationProfileTitleTest = findViewById(R.id.organizationProfileSetupTitle);
        SharedPreferences sp = getApplicationContext()
                .getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);
        String name = sp.getString("organizationNameStr","");
        organizationProfileTitleTest.setText(name);
        // /getting stored data
 // / shared pref
//nav
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(organizationProfile.this, MainActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(homeIntent);
                        break;

                    case R.id.nav_profileDrawer:

                        Intent profileIntent = new Intent(organizationProfile.this, organizationProfile.class); //changed both o.c.e.a's from main activity
                        startActivity(profileIntent);
                        break;


                    case R.id.nav_eventDrawer:

                        Intent eventIntent = new Intent(organizationProfile.this, organizationCreateEventActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(eventIntent);
                        break;

                    case R.id.nav_volunteerSearchDrawer:

                        Intent searchIntent = new Intent(organizationProfile.this, volunteerSearch.class);
                        startActivity(searchIntent);
                        break;

                    case R.id.nav_main2Drawer:

                        Intent main2Intent = new Intent(organizationProfile.this, MainActivity2.class);
                        startActivity(main2Intent);
                        break;


                    case R.id.nav_main3Drawer:

                        Intent main3Intent = new Intent(organizationProfile.this, MainActivity3.class);
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

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/detail?id=" + getPackageName();
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
        profileDrawerLayout = findViewById(R.id.profileDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, profileDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        profileDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

// /nav

    }
}