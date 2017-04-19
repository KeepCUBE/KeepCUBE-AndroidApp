package com.keepcube.keepcube.Tools.DataManager;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class Device {
    // Constants
    public static int LED_STRIP = 1;
    public static int LED_LAMP = 2;
    public static int KC_SENSE = 3;

    int type = -1;
    String name = "";

    /**
     * @param type type of device
     * @param name name of device
     */
    public Device(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public String asd() {
        return name;
    }


}