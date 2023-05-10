package com.example.afgproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public final class UserData {
    private static ArrayList<String> skills = new ArrayList<String>();
    private static ArrayList<String> interests;
    private static String zipCode;

    private static SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getString(R.string.user_preferences_map), Context.MODE_PRIVATE);

    public static String getZipCode(){
        return sharedPreferences.getString("zipCode", "00000");
    }

    public static ArrayList<String> getSkills(){
        return new ArrayList<String>(sharedPreferences.getStringSet("skills", new HashSet<>()));
    }

    public static ArrayList<String> getInterests(){
        return new ArrayList<String>(sharedPreferences.getStringSet("interests", new HashSet<>()));
    }

    public static int getMaxDistance(){
        return sharedPreferences.getInt("maxDist", 50);
    }

    public static Address getLocation() throws IOException {
        return new Geocoder(MyApplication.getContext()).getFromLocationName(getZipCode(), 1).get(0);
    }
}
