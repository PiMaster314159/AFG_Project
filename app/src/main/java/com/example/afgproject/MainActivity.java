package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //nav
    DrawerLayout mainDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    // /nav
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //stick other code here


    //nav
    setUpToolbar();

    navigationView =(NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
        switch (menuItem.getItemId())
        {
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
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

// /nav

    }
}