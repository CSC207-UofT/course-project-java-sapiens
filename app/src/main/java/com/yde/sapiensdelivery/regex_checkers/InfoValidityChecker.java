package com.yde.sapiensdelivery.regex_checkers;

public class InfoValidityChecker {
    /**
     * Checks if phoneNum is in valid phone number format.
     *
     * @param phoneNum a sting representing a phone number
     * @return whether phoneNum is in valid phone number format
     */
    public static boolean isPhoneNumValid(String phoneNum) {
        if (phoneNum == null) {
            return false;
        }
        else return phoneNum.matches("[0-9]{10}");
    }

    /**
     * Checks if sin is in valid SIN number format.
     *
     * @param sin a sting representing a SIN number
     * @return whether sin is in valid SIN number format
     */
    public static boolean isSinValid(String sin) {
        if (sin == null) {
            return false;
        }
        else return sin.matches("[0-9]{9}");
    }

    /**
     * Checks if plate is in valid Canadian license plate format.
     * Canadian license plate allows for 2-8 characters in capital letters and numbers.
     *
     * @param plate a sting representing a car license plate number
     * @return whether plate is in valid SIN number format
     */
    public static boolean isLicensePlateValid(String plate) {
        if (plate == null) {
            return false;
        }
        // Canadian license plates can be 2-8 characters in capital letters and numbers
        else return plate.matches("[A-Z|0-9]{2,8}");
    }
}
