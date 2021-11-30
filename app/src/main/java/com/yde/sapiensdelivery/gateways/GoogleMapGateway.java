package com.yde.sapiensdelivery.gateways;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONException;

public class GoogleMapGateway {

    private final String apikey = "AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";

    public static void main(String[] args) throws IOException{
        URLConnection connection = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M").openConnection();
        try(Scanner scanner = new Scanner(connection.getInputStream());){
            String response = scanner.useDelimiter("\\A").next();
            System.out.println(response);
        }
    }
}