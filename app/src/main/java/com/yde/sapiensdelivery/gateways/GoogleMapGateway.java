package com.yde.sapiensdelivery.gateways;

import com.yde.sapiensdelivery.use_cases.Locator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class GoogleMapGateway implements Locator {


    public GoogleMapGateway() {
    }

    public HashMap<String, String> findRouteInfo(String origin,
                                                 String destination)
                                                 throws IOException, JSONException {

        HashMap<String, String> routeInfo = new HashMap<String, String>();
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                origin +
                "&destination=" +
                destination +
                "&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";

        JSONObject json = this.readJsonFromUrl(url);

        JSONObject info = json.getJSONArray("routes").
                getJSONObject(0).
                getJSONArray("legs").
                getJSONObject(0);

        String duration = info.getJSONObject("duration").getString("text");
        String distance = info.getJSONObject("distance").getString("text");
        routeInfo.put("Duation", duration);
        routeInfo.put("Distance", distance);
        return routeInfo;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = this.readAll(rd);
            return new JSONObject(jsonText);
        }
    }

//    public static void main(String[] args) throws IOException, JSONException {
//        GoogleMapGateway gmg = new GoogleMapGateway();
//        HashMap<String,String> routeInfo = gmg.findRouteInfo("DundasWest",
//                "UniversityofToronto");
//        System.out.println(routeInfo);
//    }

}