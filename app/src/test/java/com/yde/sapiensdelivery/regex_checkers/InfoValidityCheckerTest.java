package com.yde.sapiensdelivery.regex_checkers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InfoValidityCheckerTest {
    @Test
    public void isPhoneNumValidTrue() {
        String phoneNum = "1231231234";
        assertTrue(InfoValidityChecker.isPhoneNumValid(phoneNum));
    }

    @Test
    public void isPhoneNumValidFalse() {
        String phoneNum = "123123A234";
        assertFalse(InfoValidityChecker.isPhoneNumValid(phoneNum));
    }

    @Test
    public void isSinValidTrue() {
        String sin = "123456789";
        assertTrue(InfoValidityChecker.isSinValid(sin));
    }

    @Test
    public void isSinValidFalse() {
        String sin = "123";
        assertFalse(InfoValidityChecker.isSinValid(sin));
    }

    @Test
    public void isLicensePlateValidTrue() {
        String plate = "MOM5CAR";
        assertTrue(InfoValidityChecker.isLicensePlateValid(plate));
    }


    @Test
    public void isLicensePlateValidFalse() {
        String plate = "Mom5car";
        assertFalse(InfoValidityChecker.isLicensePlateValid(plate));
    }
}