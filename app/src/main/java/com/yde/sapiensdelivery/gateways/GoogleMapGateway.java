package com.yde.sapiensdelivery.gateways;

//import java.io.IOException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Scanner;
//
//import org.json.JSONException;
//
//public class GoogleMapGateway {
//
//    private final String apikey = "AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M";
//
//    public static void main(String[] args) throws IOException{
//        URLConnection connection = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyAxeqdWPsIhW7KXVSef1uH0OmAX8Pnqb2M").openConnection();
//        try(Scanner scanner = new Scanner(connection.getInputStream());){
//            String response = scanner.useDelimiter("\\A").next();
//            System.out.println(response);
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class GoogleMapGateway {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            System.out.println(jsonText);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the JSON URL :");
        String url = in.next();
        JSONObject json = readJsonFromUrl(url);
        int op1= Integer.parseInt((String)json.get("Operand1"));
        int op2= Integer.parseInt((String)json.get("Operand2"));
        int result = op1+op2;
        System.out.println("Result is : "+ result);
    }


}