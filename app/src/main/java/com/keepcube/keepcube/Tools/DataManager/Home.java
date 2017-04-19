package com.keepcube.keepcube.Tools.DataManager;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class Home {
    //        static ArrayList<Device> deviceList = new ArrayList<>();
//        static ArrayList<Room> roomList = new ArrayList<>();
    static ArrayMap<String, Room> roomList = new ArrayMap<>();
    // TODO: 19.04.2017 userList
    ArrayList<String> macList = new ArrayList<String>();


    static String TAG = "Home";

    public Home() {
        // TODO: 19.04.2017 nazev domu, url keepcuby, atd
    }

    public static void addEmptyRoom(String name) {
        Room room = new Room(name);
        roomList.put(room.getName(), room);
    }

    public static void update() {
        // TODO: 19.04.2017 stahnuti a rozdeleni dat z api
    }

    public static Room room(String name) {
        return roomList.get(name);
    }

    public void addRoom(String name, Room room) {
        roomList.put(name, room);
    }

    public void removeRoom(String name) {
        roomList.remove(name);
    }

    public ArrayMap<String, Room> getRoomList() {
        return roomList;
    }


//    @Deprecated
//    public void addDevice(Device device, String roomName) {
//
//        Room room = roomList.get(roomName);
//
//        room.addDevice(device);
//
//
//        roomList.put(roomName, room);
//
//
////            deviceList.add(device);
//    }

    @Deprecated
    public ArrayList<String> getRoomNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < roomList.size(); i++) names.set(i, roomList.keyAt(i));
        return names;
    }

    public static String getRoomNameByIndex(int index) {
        String name = "";

        name = roomList.keyAt(index);


        return name;
    }

    public static int getNumberOfRooms() {
        return roomList.size();
    }





    public static void update(final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, "http://httpstat.us/200",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toasty.success(context, "Updated successfully", Toast.LENGTH_SHORT, true).show();
//                                Users.namesList.add();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: hmmmmmmmmm :(");
                                Toasty.error(context, "Base unreachable", Toast.LENGTH_SHORT, true).show();

                            }
                        }) {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        Log.d(TAG, "responseCode : " + response.statusCode);

                        String utf8 = null;
                        try {
                            utf8 = new String(response.data, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        return Response.success(utf8, HttpHeaderParser.parseCacheHeaders(response));
                    }
                });

            }
        }, "DataUpdateThread").start();

    }





}