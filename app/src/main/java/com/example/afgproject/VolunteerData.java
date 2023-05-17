package com.example.afgproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class used to access the shared preferences of the user
 */
public final class VolunteerData {
    private static SharedPreferences sharedPreferences;

    /**
     * @return Zip code of the user
     * Default value is the location of Mass Academy
     */
    public static String getZipCode(){
        return sharedPreferences.getString("zipCode", "01604");
    }

    /**
     * @return Specified volunteer skills
     */
    public static ArrayList<String> getSkills(){
        return new ArrayList<String>(sharedPreferences.getStringSet("skills", new HashSet<>()));
    }

    /**
     * @return Specified volunteer interests
     */
    public static ArrayList<String> getInterests(){
        return new ArrayList<String>(sharedPreferences.getStringSet("interests", new HashSet<>()));
    }

    /**
     * @return Maximum distance which the user wishes to search for organizations/activities
     */
    public static float getMaxDistance(){
        System.out.println("ok this is fine");
        return sharedPreferences.getFloat("maxDist", 50.0f);
    }

    public static Address getLocation() throws IOException {
        return new Geocoder(MyApplication.getContext()).getFromLocationName(getZipCode(), 1).get(0);
    }

    /**
     * Push the user interests to the shared preferences
     * @param interests - Interests specified by the volunteer
     */
    public static void putInterests(ArrayList<String> interests){
        System.out.println("Shared preferences " + sharedPreferences);
        System.out.println("Get interests " + getInterests());
        sharedPreferences.edit().putStringSet("interests", new HashSet<>(interests)).apply();
    }

    /**
     * Push the user skills to the shared preferences
     * @param skills - Skills specified by the volunteer
     */
    public static void putSkills(ArrayList<String> skills){
        sharedPreferences.edit().putStringSet("skills", new HashSet<>(skills)).apply();
    }

    /**
     * Push the maximum distance that the user wishes for organizations/activities to be recommended from
     * @param distance
     */
    public static void putMaxDistance(double distance){
        sharedPreferences.edit().putFloat("maxDist", (float) distance).apply();
    }

    /**
     * Push the zip code of the user
     * @param zipCode - Zip code of the user
     */
    public static void putZipCode(String zipCode){
        sharedPreferences.edit().putString("zipCode", zipCode).apply();
    }

    /**
     * Create shared preferences directory when creating the startup menu to ensure that no errors result from querying this object
     */
    public static void setSharedPreferences(){
        sharedPreferences = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getString(R.string.user_preferences_map), Context.MODE_PRIVATE);
        System.out.println(sharedPreferences);
    }

    /**
     * When needed, clear this shared preferences directory such that all user data is whiped
     */
    public static void clearSharedPreferences(){
        sharedPreferences.edit().clear().apply();
    }
}
