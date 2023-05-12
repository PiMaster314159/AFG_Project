package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity2 extends AppCompatActivity {

    //nav
    DrawerLayout main2DrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    // /nav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//nav
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(MainActivity2.this, MainActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(homeIntent);
                        break;

                    case R.id.nav_profileDrawer:

                        Intent profileIntent = new Intent(MainActivity2.this, organizationProfile.class); //changed both o.c.e.a's from main activity
                        startActivity(profileIntent);
                        break;


                    case R.id.nav_eventDrawer:

                        Intent eventIntent = new Intent(MainActivity2.this, organizationCreateEventActivity.class); //changed both o.c.e.a's from main activity
                        startActivity(eventIntent);
                        break;

                    case R.id.nav_volunteerSearchDrawer:

                        Intent searchIntent = new Intent(MainActivity2.this, volunteerSearch.class);
                        startActivity(searchIntent);
                        break;

                    case R.id.nav_main2Drawer:

                        Intent main2Intent = new Intent(MainActivity2.this, MainActivity2.class);
                        startActivity(main2Intent);
                        break;


                    case R.id.nav_main3Drawer:

                        Intent main3Intent = new Intent(MainActivity2.this, MainActivity3.class);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    public void setUpToolbar() {
        main2DrawerLayout = findViewById(R.id.main2DrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity2.this, main2DrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        main2DrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}