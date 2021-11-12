package ui;

import controllers.SystemInOut;

import java.util.ArrayList;

/**
 * Used to replicate the Activity class in Android
 * Daughter classes also are prototypes for Android counterparts
 * in phase2.
 */
public interface Activity {

    SystemInOut sio = new SystemInOut();

    void display();
    void getData(Object transferredData);
}
