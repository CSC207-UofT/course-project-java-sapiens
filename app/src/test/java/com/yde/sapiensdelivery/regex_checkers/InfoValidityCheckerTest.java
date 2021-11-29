package com.yde.sapiensdelivery.regex_checkers;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InfoValidityCheckerTest {
    @Test
    public void isDeliveryManInfoInvalidTrue() {
        HashMap<String, String> fieldToValue = new HashMap<>();
        fieldToValue.put("PHONE NUMBER", "1231231234");
        fieldToValue.put("SIN", "123456789");
        fieldToValue.put("TRANSPORT", "MOM5CAR");
        assertTrue(InfoValidityChecker.isDeliveryManInfoInvalid(fieldToValue));
    }

    @Test
    public void isDeliveryManInfoInvalidFalse() {
        HashMap<String, String> fieldToValue = new HashMap<>();
        fieldToValue.put("PHONE NUMBER", "123");
        fieldToValue.put("SIN", "12345678910");
        fieldToValue.put("TRANSPORT", "M");
        assertFalse(InfoValidityChecker.isDeliveryManInfoInvalid(fieldToValue));
    }
}