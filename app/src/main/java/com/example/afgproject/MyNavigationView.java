package com.example.afgproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
        navigationView.getMenu().clear();
        int accountType = activity.getSharedPreferences("Profile", Context.MODE_PRIVATE).getInt("ProfileType", 0);
        switch (accountType){
            case 1:
                navigationView.inflateMenu(R.menu.volunteer_drawer_menu);
                setUpVolunteerNavigationView();
                break;
            case 2:
                navigationView.inflateMenu(R.menu.organization_drawer_menu);
                setUpOrganizationNavigationView();
                break;

        }
    }
    public void setUpOrganizationNavigationView() {
        setUpToolbar(R.string.organization_home_page_header);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(context, OrganizationHome.class);
                        context.startActivity(homeIntent);
                        break;

                    case R.id.create_event:

                        Intent profileIntent = new Intent(context, UploadActivity.class);
                        context.startActivity(profileIntent);
                        break;


                    case R.id.edit_profile:
                        Intent eventIntent = new Intent(context, OrganizationProfilePreferences.class);
                        context.startActivity(eventIntent);
                        break;

                    case R.id.nav_share: {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/detail?id=" + context.getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
                        break;
                    }
                    case R.id.clear_data: {
                        VolunteerData.clearSharedPreferences();
                        activity.getSharedPreferences("Profile", Context.MODE_PRIVATE).edit().clear().apply();
                        Intent intent = new Intent(context, StartMenu.class);
                        context.startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }
    public void setUpVolunteerNavigationView() {
        setUpToolbar(R.string.volunteer_home_page_header);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home: {

                        Intent homeIntent = new Intent(context, VolunteerHome.class);
                        context.startActivity(homeIntent);
                        break;
                    }

                    case R.id.edit_profile: {
                        Intent eventIntent = new Intent(context, VolunteerProfilePreferences.class);
                        context.startActivity(eventIntent);
                        break;
                    }

                    case R.id.nav_share: {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/detail?id=" + context.getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
                        break;
                    }
                    case R.id.clear_data: {
                        VolunteerData.clearSharedPreferences();
                        activity.getSharedPreferences("Profile", Context.MODE_PRIVATE).edit().clear().apply();
                        Intent intent = new Intent(context, StartMenu.class);
                        context.startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }
    public void setUpToolbar() {
        profileDrawerLayout = activity.findViewById(R.id.profileDrawerLayout);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        System.out.println("Toolbar: " + toolbar);
        activity.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity.getParent(), profileDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        profileDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void setUpToolbar(int idName) {
        profileDrawerLayout = activity.findViewById(R.id.profileDrawerLayout);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        System.out.println("Toolbar: " + toolbar);
        activity.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity.getParent(), profileDrawerLayout, toolbar, idName, idName);
        profileDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
