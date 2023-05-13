package com.example.afgproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MyNavigationView {
    NavigationView navigationView;
    Context context;
    AppCompatActivity activity;
    DrawerLayout profileDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    public MyNavigationView(NavigationView navigationView){
        this.navigationView = navigationView;
        this.context = navigationView.getContext();
        this.activity = (AppCompatActivity) context;
        setUpNavigationView();
    }
    public void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                setUpToolbar();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(context, MainActivity.class);
                        context.startActivity(homeIntent);
                        break;

                    case R.id.nav_profileDrawer:

                        Intent profileIntent = new Intent(context, organizationProfile.class);
                        context.startActivity(profileIntent);
                        break;


                    case R.id.nav_eventDrawer:
                        Intent eventIntent = new Intent(context, organizationCreateEventActivity.class);
                        context.startActivity(eventIntent);
                        break;

                    case R.id.nav_volunteerSearchDrawer:

                        Intent searchIntent = new Intent(context, volunteerSearch.class);
                        context.startActivity(searchIntent);
                        break;

                    case R.id.nav_share: {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/detail?id=" + context.getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
                    }
                    break;
                }
                return false;
            }
        });
    }
    public void setUpToolbar() {
        profileDrawerLayout = activity.findViewById(R.id.profileDrawerLayout);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity, profileDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        profileDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
