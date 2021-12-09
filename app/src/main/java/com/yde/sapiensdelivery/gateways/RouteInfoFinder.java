package com.yde.sapiensdelivery.gateways;

import com.yde.sapiensdelivery.use_cases.Locator;

import org.json.JSONArray;
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

        HashMap<String, Double> routeInfo = new HashMap<>();

        String url = urlFactory(origin, destination, null ,transportation);
        JSONObject json = this.readJsonFromUrl(url);

        routeInfo.put("Duration", routeInfoParser(json, infoType.duration));
        routeInfo.put("Distance", routeInfoParser(json, infoType.distance));
        return routeInfo;
    }


    public HashMap<String, Double> findMultiRouteInfo(String origin,
                                                      String destination,
                                                      String[] waypoints,
                                                      Locator.transportation transportation)
            throws IOException, JSONException {

        HashMap<String, Double> routeInfo = new HashMap<>();

        String url = urlFactory(origin, destination, waypoints, transportation);
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

        JSONArray infos = infoJson.getJSONArray("routes").
                getJSONObject(0).
                getJSONArray("legs");

        int index = 0;
        JSONObject info;
        double unprocessed;
        while(index < infos.length()){
            info = infos.getJSONObject(index);
            switch (type){
                case duration:
                    // convert unit and round to the second decimal place
                    unprocessed = info.getJSONObject("duration").getDouble("value");
                    returnInfo = returnInfo + Math.round(unprocessed / 3600 * 100) / 100.0;
                    index = index + 1;
                    break;
                case distance:
                    // convert unit and round to the second decimal place
                    unprocessed = info.getJSONObject("distance").getDouble("value");
                    returnInfo = returnInfo + Math.round(unprocessed / 1000 * 100) / 100.0;
                    index = index + 1;
                    break;
            }
        }

        return returnInfo;
    }

    /**
     * A factory method for building different types of url required for findRouteInfo.
     */
    private String urlFactory(String origin,
                              String destination,
                              String[] waypoints,
                              Locator.transportation transportation){
        String url = "";
        StringBuilder points = new StringBuilder();

        origin = origin.replaceAll(" ", "%");
        destination = destination.replaceAll(" ", "%");


        if (waypoints != null){
            points.append("&waypoints=optimize:true|");
            for (String point : waypoints){
                point = point.replaceAll(" ", "%");
                points.append(point).append("|");
            }
        }

        switch (transportation){
            case walking:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        points.toString() +
                        "&mode=walking" +
                        "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
                break;
            case driving:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        points.toString() +
                        "&mode=driving" +
                        "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
                break;
            case bicycling:
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                        origin +
                        "&destination=" +
                        destination +
                        points.toString() +
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
