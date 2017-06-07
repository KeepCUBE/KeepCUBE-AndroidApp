package com.keepcube.keepcube.Tools.DataManager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class Room {
    //    ArrayMap<Integer, Device> deviceList = new ArrayMap<>();
    public static ArrayList<Device> deviceList = new ArrayList<>();

    int id = -1;
    String name = "null";
    String description = "null";


//    public Room(ArrayList<Device> deviceList) {
//        this.deviceList = deviceList;
//    }

    public Room(@NonNull int db_id,@NonNull String db_name,@Nullable String db_description) {
        this.id = db_id;
        this.name = db_name;
        this.description = db_description;


    }


    public void addDevice(String name, int device_id, int type_id, int mesh_addr) {
//        deviceList.put(device_id, new Device(name, device_id, type_id, mesh_addr));

        if (!deviceList.contains(new Device(name, device_id, type_id, mesh_addr))) {

            deviceList.add(new Device(name, device_id, type_id, mesh_addr));

        }
        else {
            System.out.println("addDevice unsuccessful!!!");
        }



    }


//    @Deprecated // nastavit na ID !!!
//    public Device device(String name) {
//
//        for (int i = 0; i < deviceList.size(); i++) {
//            Device device = deviceList.get(i);
//            if (device.name.equals(name)) return device;
//        }
//
//        return deviceList.get(name);
//
//    }

//    public Device device(int index) {
//        return deviceList.get(index);
//    }

    public String getName() {
        return this.name;
    }

//    @Deprecated // protože tam byl problém s updatováním
//    public void setName(String name) {
//        this.name = name;
//    }


    // TODO: 22.04.2017 jeste udelat podle ID, ne jenom podle indexu!

    public String getDeviceNameByIndex(int index) {
        return deviceList.get(index).name;
    }

    public String getDeviceIDByIndex(int index) {
        return String.valueOf(deviceList.get(index).device_id);
    }

    public String getDeviceTypeByIndex(int index) {
        return String.valueOf(deviceList.get(index).type_id);
    }

    public String getDeviceMeshAddrByIndex(int index) {
        return String.valueOf(deviceList.get(index).mesh_addr);
    }

//    @Deprecated
//    public String getDeviceIDByName(String name) {
//
//
//        return deviceList.keyAt(index);
//    }

    public int numberOfDevices() {
        return deviceList.size();
    }


}