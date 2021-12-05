package com.yde.sapiensdelivery.gateways;

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

public class RouteInfoFinder {

    // Private enum for different types of information
    private enum infoType {
        duration, distance
    }

    public RouteInfoFinder() {
    }

    public HashMap<String, Double> findRouteInfo(String origin,
                                                 String destination,
                                                 Locator.transportation transportation)
            throws IOException, JSONException {

        HashMap<String, Double> routeInfo = new HashMap<String, Double>();

        String url = urlFactory(origin, destination, transportation);
        JSONObject json = this.readJsonFromUrl(url);

        routeInfo.put("Duration", routeInfoParser(json, infoType.duration));
        routeInfo.put("Distance", routeInfoParser(json, infoType.distance));
        return routeInfo;
    }


    /**
     * Return a string representation of the specified information type for findRouteInfo.
     */
    private double routeInfoParser(JSONObject infoJson, infoType type) throws JSONException{
        double returnInfo = 0;
        JSONObject info = infoJson.getJSONArray("routes").
                getJSONObject(0).
                getJSONArray("legs").
                getJSONObject(0);

        switch (type){
            case duration:
                returnInfo = info.getJSONObject("duration").getDouble("value");
                returnInfo = Math.round(returnInfo / 3600 * 100) / 100.0;
                break;
            case distance:
                returnInfo = info.getJSONObject("distance").getDouble("value");
                returnInfo = Math.round(returnInfo / 1000 * 100) / 100.0;
                break;
        }
        return returnInfo;
    }

    /**
     * A factory method for building different types of url required for findRouteInfo.
     */
    private String urlFactory(String origin,
                              String destination,
                              Locator.transportation transportation){
        String url = "";

        origin = origin.replaceAll(" ", "%");
        destination = destination.replaceAll(" ", "%");

        switch (transportation){
            case walking:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        "&mode=walking" +
                        "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
                break;
            case driving:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        "&mode=driving" +
                        "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
                break;
            case bicycling:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        "&mode=bicycling" +
                        "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
                break;
        }
        return url;
    }

    /**
     * read the content in a json file.
     */
    private String readAll(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int line;
        while ((line = reader.read()) != -1) {
            builder.append((char) line);
        }
        return builder.toString();
    }

    /**
     * Return a JSONObject representing the json file get from url.
     */
    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = this.readAll(rd);
            return new JSONObject(jsonText);
        }
    }
}
