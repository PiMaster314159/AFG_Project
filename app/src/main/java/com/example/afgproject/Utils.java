package com.example.afgproject;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.ArrayList;

public final class Utils {
    public static double getDistance(String zipCode1, String zipCode2) throws IOException {
        Address address1 = getAddress(zipCode1);
        Address address2 = getAddress(zipCode2);
        Location location1 = new Location("userLocation");
        location1.setLatitude(address1.getLatitude());
        location1.setLongitude(address1.getLongitude());

        Location location2 = new Location("activityLocation");
        location2.setLatitude(address2.getLatitude());
        location2.setLongitude(address2.getLongitude());
        return metersToMiles(location1.distanceTo(location2));
    }

    private static double metersToMiles(double meters){
        double CONVERSION_UNIT = 0.00062137119;
        return meters * CONVERSION_UNIT;
    }

    private static Address getAddress(String zipCode) throws IOException {
        return new Geocoder(MyApplication.getContext()).getFromLocationName(zipCode, 1).get(0);
    }

    public static ArrayList<ObjectMap> createObjectMapList(String[] keys, Object[][] valueArr){
        ArrayList<ObjectMap> objectMapArrayList = new ArrayList<ObjectMap>();
        for(Object[] values : valueArr){
            objectMapArrayList.add(new ObjectMap(keys, values));
        }
        return objectMapArrayList;
    }
    public static ArrayList<ObjectMap> createObjectMapList(String[] keys, ArrayList<Object[]> valueArrList){
        ArrayList<ObjectMap> objectMapArrayList = new ArrayList<ObjectMap>();
        for(Object[] values : valueArrList){
            objectMapArrayList.add(new ObjectMap(keys, values));
        }
        return objectMapArrayList;
    }


}
