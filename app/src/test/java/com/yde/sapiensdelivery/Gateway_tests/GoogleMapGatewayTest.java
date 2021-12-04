package com.yde.sapiensdelivery.Gateway_tests;

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
        HashMap<String, String> result = gmg.findRouteInfo("Dundas West",
                                                "University of Toronto",
                                                           Locator.transportation.driving);
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("Duration", "32 mins");
        expected.put("Distance", "28.2 km");

        assertEquals(expected, result);
    }

    @Test
    public void testFindRouteInfoWalking() throws IOException, JSONException {
        HashMap<String, String> result = gmg.findRouteInfo("Dundas West",
                                                "University of Toronto",
                                                           Locator.transportation.walking);
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("Duration", "5 hours 7 mins");
        expected.put("Distance", "24.4 km");

        assertEquals(expected, result);
    }
}