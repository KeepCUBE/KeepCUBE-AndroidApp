package com.keepcube.keepcube.Tools.DataManager;

import java.util.ArrayList;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class User {
     ArrayList<String> namesList = new ArrayList<String>();
     ArrayList<String> ipList = new ArrayList<String>();
     ArrayList<String> macList = new ArrayList<String>();


    public  boolean add(String name, String ip, String mac) {
        if (namesList.contains(name)) return false;

        namesList.add(name);
        ipList.add(ip);
        macList.add(mac);
        // TODO: 17.04.2017 Btw, co jen poslat request a updatnout to? :D

        return true;
    }

    public  String getName(int index) {
        return namesList.get(index);
    }

    public  String getIP(int index) {
        return ipList.get(index);
    }

    public  String getMAC(int index) {
        return macList.get(index);
    }

    public  int getIndexByName(String name) {
        return namesList.lastIndexOf(name);
    }

    public  int getIndexByIP(String ip) {
        return ipList.lastIndexOf(ip);
    }

    public  int getIndexByMAC(String mac) {
        return macList.lastIndexOf(mac);
    }

    public  int getUsersCount() {
        int x = namesList.size();
        int y = ipList.size();
        int z = macList.size();

        if (x == y && y == z) {
            return x;
        } else return -1;
    }

    @Deprecated
    public  void remove(String name) {
        // TODO: 17.04.2017 udelat aby to vracelo bool o tom, jestli se odebral uspesne
        int index = namesList.lastIndexOf(name);

        namesList.remove(index);
        ipList.remove(index);
        macList.remove(index);
    }


}