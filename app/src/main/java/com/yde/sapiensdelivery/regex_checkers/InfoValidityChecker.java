package com.yde.sapiensdelivery.regex_checkers;

import java.util.HashMap;

public class InfoValidityChecker {
    /**
     * Checks if the value for a certain field is a valid entry.
     * Concerned with fields: Phone Number, SIN and License Plate (transport)
     *
     * @param fieldToValue Hashmap containing the above fields.
     * @return if all input fields are legal.
     */
    public static boolean isDeliveryManInfoInvalid(HashMap<String, String> fieldToValue) {
        String phoneNum = fieldToValue.get("PHONE NUMBER");
        if (phoneNum == null) {
            return false;
        }
        else if (!phoneNum.matches("[0-9]{10}")) {
            return false;
        }

        String sinNum = fieldToValue.get("SIN");
        if (sinNum == null) {
            return false;
        }
        else if (!sinNum.matches("[0-9]{9}")) {
            return false;
        }

        String plateVal = fieldToValue.get("TRANSPORT");
        // Canadian plates
        if (plateVal == null) {
            return false;
        }
        // Canadian license plates can be 2-8 characters in capital letters and numbers
        else return plateVal.matches("[A-Z|0-9]{2,8}");
    }
}
