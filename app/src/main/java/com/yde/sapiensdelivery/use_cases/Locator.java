package com.yde.sapiensdelivery.use_cases;

import org.json.JSONException;
import java.io.IOException;
import java.util.HashMap;

public interface Locator {
    HashMap<String,String> findRouteInfo(String customerLocation,
                                         String deliverymanLocation)
                                         throws IOException, JSONException;
}
