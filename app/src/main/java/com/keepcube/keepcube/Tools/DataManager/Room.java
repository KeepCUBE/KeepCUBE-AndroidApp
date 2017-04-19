package com.keepcube.keepcube.Tools.DataManager;

import android.support.v4.util.ArrayMap;

/**
 * Created by Ondrej on 19.04.2017.
 */

public  class Room {
    ArrayMap<String, Device> deviceList = new ArrayMap<>();
    String name = "";

//    public Room(ArrayList<Device> deviceList) {
//        this.deviceList = deviceList;
//    }

    public Room(String name) {
        this.name = name;
    }




    public void addDevice(int type, String name) {
        deviceList.put(name, new Device(type, name));
    }

    public Device device(String name) {
        return deviceList.get(name);

    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDeviceNameByIndex(int index) {
        return deviceList.keyAt(index);
    }

    public int numberOfDevices() {
        return deviceList.size();
    }


}