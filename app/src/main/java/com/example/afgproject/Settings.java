package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FirebaseRecyclerView interestRecycler = new FirebaseRecyclerView("Interests", "name");
        FirebaseRecyclerView skillsRecycler = new FirebaseRecyclerView("Skills", "name");
        transaction.replace(R.id.interests, interestRecycler);
        transaction.replace(R.id.skills, skillsRecycler);
        transaction.commit();
    }
}