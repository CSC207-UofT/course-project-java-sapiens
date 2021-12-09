package com.yde.sapiensdelivery.gateways;

import android.app.Activity;

import com.yde.sapiensdelivery.use_cases.Locator;

import org.json.JSONException;

import java.io.IOException;
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

    /**
     * Return a String representation of the coordinates of current location
     * @param activity The activity that is calling this method.
     * @return a String of the following structure:
     * "Latitude,Longitude"
     */
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

    /**
     * Return a hashmap of the duration (in hours) and the distance (in km) of the route
     * between origin and destination according to the specified transportation.
     * @param origin The starting location of the route
     * @param destination the ending location of the route
     * @param waypoints The location of stopovers between the origin and destination.
     * @param transportation the type of transportation used.
     * @return a hashMap of the following structure:
     * {"Distance": double, "Duration": double}
     */
    public HashMap<String, Double> findMultiRouteInfo(String origin,
                                                 String destination,
                                                 String[] waypoints,
                                                 transportation transportation)
                                                 throws IOException, JSONException {

        return routeInfoFinder.findMultiRouteInfo(origin, destination, waypoints,transportation);

    }

}