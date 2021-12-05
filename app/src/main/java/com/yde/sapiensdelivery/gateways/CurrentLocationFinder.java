package com.yde.sapiensdelivery.gateways;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class CurrentLocationFinder {

    /**
     * Return a String representation of the coordinates of current location
     * @param activity The activity that is calling this method.
     * @return a String of the following structure:
     * "Latitude,Longitude"
     */
    public String findCurrentLocation(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        // Checking if location permission is granted.
        // Ask for permission if not.
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1);
        }

        // Getting location
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        return lat+","+lng;
    }

}
