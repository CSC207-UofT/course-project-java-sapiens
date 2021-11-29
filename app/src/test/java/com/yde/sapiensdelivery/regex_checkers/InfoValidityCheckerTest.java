package com.yde.sapiensdelivery.regex_checkers;

import com.yde.sapiensdelivery.gateways.DeliveryManGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class InfoValidityCheckerTest {
    @Test
    public void isDeliveryManInfoInvalidTrue() {
        HashMap<String, String> fieldToValue = new HashMap<String, String>();
        fieldToValue.put("PHONE NUMBER", "1231231234");
        fieldToValue.put("SIN", "123456789");
        fieldToValue.put("TRANSPORT", "MOM5CAR");
        assertTrue(InfoValidityChecker.isDeliveryManInfoInvalid(fieldToValue));
    }

    @Test
    public void isDeliveryManInfoInvalidFalse() {
        HashMap<String, String> fieldToValue = new HashMap<String, String>();
        fieldToValue.put("PHONE NUMBER", "123");
        fieldToValue.put("SIN", "12345678910");
        fieldToValue.put("TRANSPORT", "M");
        assertFalse(InfoValidityChecker.isDeliveryManInfoInvalid(fieldToValue));
    }
}