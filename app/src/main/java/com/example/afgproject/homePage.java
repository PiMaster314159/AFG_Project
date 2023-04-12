package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        GridFragment gRecycler = new GridFragment();
        transaction2.replace(R.id.postList, gRecycler);
        transaction2.commit();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        HorizontalFragment hRecycler = new HorizontalFragment();
        transaction.replace(R.id.organizationList, hRecycler);
        transaction.commit();
    }
}