package com.example.afgproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public final class VolunteerSharedData {

    private static final SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getString(R.string.user_preferences_map), Context.MODE_PRIVATE);

    public static String getZipCode(){
        return sharedPreferences.getString("zipCode", "00000");
    }

    public static ArrayList<String> getSkills(){
        return new ArrayList<String>(sharedPreferences.getStringSet("skills", new HashSet<>()));
    }

    public static ArrayList<String> getInterests(){
        return new ArrayList<String>(sharedPreferences.getStringSet("interests", new HashSet<>()));
    }

    public static float getMaxDistance(){
        System.out.println("ok this is fine");
        return sharedPreferences.getFloat("maxDist", 0.0f);
    }

    public static Address getLocation() throws IOException {
        return new Geocoder(MyApplication.getContext()).getFromLocationName(getZipCode(), 1).get(0);
    }

    public static void putInterests(ArrayList<String> interests){
        sharedPreferences.edit().putStringSet("interests", new HashSet<>(interests)).apply();
    }
    public static void putSkills(ArrayList<String> skills){
        sharedPreferences.edit().putStringSet("skills", new HashSet<>(skills)).apply();
    }
    public static void putMaxDistance(double distance){
        sharedPreferences.edit().putFloat("maxDist", (float) distance).apply();
    }
    public static void putZipCode(String zipCode){
        sharedPreferences.edit().putString("zipCode", zipCode).apply();
    }
}
