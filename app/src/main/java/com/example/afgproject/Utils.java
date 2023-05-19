package com.example.afgproject;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;

/**
 * Utilities class for app
 */
public class Utils {
    /**
     * Calculate the distance between two points using the Geocoder class
     * @param zipCode1 - Zip code of user
     * @param zipCode2 - Zip code of opportunity
     * @return distance in miles between user and opportunity
     * @throws IOException
     */
    public static double getDistance(String zipCode1, String zipCode2) throws IOException {

        Address address1 = getAddress(zipCode1);
        Address address2 = getAddress(zipCode2);
        if (address1 == null || address2 == null)
            return Double.MAX_VALUE;
        Location location1 = new Location("userLocation");
        location1.setLatitude(address1.getLatitude());
        location1.setLongitude(address1.getLongitude());

        System.out.println(location1);

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
        System.out.println("Addresses " + new Geocoder(MyApplication.getContext()).getFromLocationName(zipCode, 1));
        return new Geocoder(MyApplication.getContext()).getFromLocationName(zipCode, 1).get(0);
    }

    public static boolean isValidZipCode(String zipCode) throws IOException {
        if(zipCode.equals("")) return false;
        return (new Geocoder(MyApplication.getContext()).getFromLocationName(zipCode, 1).size() > 0);
    }
}
