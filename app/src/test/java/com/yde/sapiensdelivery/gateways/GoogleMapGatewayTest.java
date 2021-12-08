package com.yde.sapiensdelivery.gateways;

import com.yde.sapiensdelivery.gateways.GoogleMapGateway;
import com.yde.sapiensdelivery.use_cases.Locator;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class GoogleMapGatewayTest {

    private GoogleMapGateway gmg;

    @Before
    public void setUp() {
        gmg = new GoogleMapGateway();
    }

    @Test
    public void testFindRouteInfoDriving() throws IOException, JSONException {
        HashMap<String, Double> result = gmg.findRouteInfo("Dundas West",
                                                "University of Toronto",
                                                           Locator.transportation.driving);
        HashMap<String, Double> expected = new HashMap<String, Double>();
        expected.put("Duration", 0.53);
        expected.put("Distance", 28.2);

        assertEquals(expected, result);
    }

    @Test
    public void testFindRouteInfoWalking() throws IOException, JSONException {
        HashMap<String, Double> result = gmg.findRouteInfo("Dundas West",
                                                "University of Toronto",
                                                           Locator.transportation.walking);
        HashMap<String, Double> expected = new HashMap<String, Double>();
        expected.put("Duration", 5.11);
        expected.put("Distance", 24.44);

        assertEquals(expected, result);
    }


    @Test
    public void testFindMultiRouteInfoDriving() throws IOException, JSONException {
        String[] waypoints = {"High Park,Canada", "M4P2K1"};
        HashMap<String, Double> result = gmg.findMultiRouteInfo("Dundas West",
                "University of Toronto",
                waypoints,
                Locator.transportation.driving);
        HashMap<String, Double> expected = new HashMap<String, Double>();
        expected.put("Duration", 1.12);
        expected.put("Distance", 48.34);

        assertEquals(expected, result);
    }
}