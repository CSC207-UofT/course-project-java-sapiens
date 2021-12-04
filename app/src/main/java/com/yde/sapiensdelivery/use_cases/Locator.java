package com.yde.sapiensdelivery.use_cases;

import org.json.JSONException;
import java.io.IOException;
import java.util.HashMap;

/**
 * A interface that represents a locator.
 *
 * can be used to get route information between two location.
 */
public interface Locator {
    // enum for different types of transportation.
    enum transportation{
        walking, driving, bicycling
    }

    /**
     * Return a hashmap of the duration (in hours) and the distance (in km) of the route
     * between origin and destination according to the specified transportation.
     *
     * @param origin The starting location of the route
     * @param destination the ending location of the route
     * @param transportation the type of transportation used.
     */
    HashMap<String, Double> findRouteInfo(String origin,
                                         String destination,
                                         transportation transportation)
                                         throws IOException, JSONException;
}
