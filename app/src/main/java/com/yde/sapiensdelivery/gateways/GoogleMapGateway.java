package com.yde.sapiensdelivery.gateways;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.yde.sapiensdelivery.use_cases.Locator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * A gateway method that gets data from the GoogleMap Direction API
 *
 * Implements the Locator Interface.
 */
public class GoogleMapGateway implements Locator {

    private final RouteInfoFinder routeInfoFinder = new RouteInfoFinder();
    private final CurrentLocationFinder currentLocationFinder = new CurrentLocationFinder();

    public GoogleMapGateway() {
    }

    public String findCurrentLocation(Activity activity) {
        return currentLocationFinder.findCurrentLocation(activity);
    }


    /**
     * Return a hashmap of the duration (in hours) and the distance (in km) of the route
     * between origin and destination according to the specified transportation.
     *  @param origin The starting location of the route
     * @param destination the ending location of the route
     * @param transportation the type of transportation used.
     * @return a hashMap of the following structure:
     * {"Distance": double, "Duration": double}
     */
    @Override
    public HashMap<String, Double> findRouteInfo(String origin,
                                                String destination,
                                                transportation transportation)
                                                 throws IOException, JSONException {

        return routeInfoFinder.findRouteInfo(origin, destination, transportation);

    }

}