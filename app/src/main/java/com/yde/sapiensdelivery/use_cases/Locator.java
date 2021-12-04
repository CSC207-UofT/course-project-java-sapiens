package com.yde.sapiensdelivery.use_cases;

import org.json.JSONException;
import java.io.IOException;
import java.util.HashMap;

public interface Locator {
    enum transportation{
        walking, driving, bicycling
    }
    HashMap<String,String> findRouteInfo(String customerLocation,
                                         String deliverymanLocation,
                                         transportation transportation)
                                         throws IOException, JSONException;
}
