package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;

public class UserPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ArrayList<ObjectMap> data = Utils.createObjectMapList(new String[]{"Widget Name"}, new String[][]{{"Hello"}, {"It's me"}});
        RecyclerTest recycler = new RecyclerTest(RecyclerTest.LayoutManagerType.LINEAR_LAYOUT_MANAGER_Vertical, data, R.layout.settings_widget, new UserPreferenceHolder());
        transaction.replace(R.id.recyclerView, recycler);
        transaction.commit();
    }
}