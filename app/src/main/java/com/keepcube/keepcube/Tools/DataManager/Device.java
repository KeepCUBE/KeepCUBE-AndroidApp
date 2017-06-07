package com.keepcube.keepcube.Tools.DataManager;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class Device {
    // Constants
    public static int SWITCH = 1;
    public static int KC_LED = 2;
//    public static int KC_SENSE = 3;

    public String name = "";
    public int device_id = -1;
    public int type_id = -1;
    public int mesh_addr = -1;

    @Deprecated
    private int room_id = -1;

    /**
     * @param name      jasný
     * @param device_id ID přidávanýho zařízení
     * @param type_id   jasný (používat konstanty této třídy)
     * @param mesh_addr adresa v mesh siti
     */
    public Device(String name, int device_id, int type_id, int mesh_addr) {
        this.name = name;
        this.device_id = device_id;
        this.type_id = type_id;
        this.mesh_addr = mesh_addr;
        // TODO: 22.04.2017 pridat created_at a updated_at
    }



    @Deprecated
    public String asd() {
        return name;
    }


    // TODO: 22.04.2017 udelat getName

}