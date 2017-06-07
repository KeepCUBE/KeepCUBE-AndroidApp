package com.keepcube.keepcube.Tools.DataManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;
import com.keepcube.keepcube.Fragment.AccessoriesFragment;
import com.keepcube.keepcube.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by Ondrej on 19.04.2017.
 */

public class Home {
    //        static ArrayList<Device> deviceList = new ArrayList<>();
//        static ArrayList<Room> roomList = new ArrayList<>();
//    static ArrayMap<String, Room> roomList = new ArrayMap<>();
    public static ArrayList<Room> roomList = new ArrayList<>();

    static String TAG = "Home";
    // TODO: 19.04.2017 userList
    ArrayList<String> macList = new ArrayList<String>();

    public Home() {
        // TODO: 19.04.2017 nazev domu, url keepcuby, atd
    }

    public static void addEmptyRoom(@NonNull int db_id, @NonNull String db_name, @Nullable String db_description) {
//        Room room = new Room(name);
//        roomList.put(room.getName(), room);

        if (!roomList.contains(new Room(db_id, db_name, db_description))) {
            roomList.add(new Room(db_id, db_name, db_description));
        }

    }

    public static Room room(int index) {
        return roomList.get(index);
    }

    public static String getRoomNameByIndex(int index) {
//        return roomList.keyAt(index);
        return roomList.get(index).name;
    }

    public static int getNumberOfRooms() {
        return roomList.size();
    }

    public static void updateAccessories(final Context context) {


        Bridge
                .get("/keepi/v1/rooms?includes=devices")
                .request(new Callback() {
                    @Override
                    public void response(@NonNull Request request, Response response, BridgeException e) {
                        if (e != null) {
                            // See the 'Error Handling' section for information on how to process BridgeExceptions
                            int reason = e.reason();
                            System.out.println(String.valueOf(reason));
                            Toasty.error(context, "Base unreachable", Toast.LENGTH_SHORT, true).show();
                        } else {
                            String responseContent = response.asString();
                            System.out.println(responseContent);


                            Room.deviceList.clear();
                            Home.roomList.clear();


                            try {
                                JSONObject json = new JSONObject(responseContent);
                                JSONArray rooms = json.getJSONArray("data");

                                for (int i = 0; i < rooms.length(); i++) {
                                    JSONObject room = rooms.getJSONObject(i);

                                    int room_id = room.getInt("id");
                                    String room_name = room.getString("name");
                                    String description = room.getString("description");

                                    Home.addEmptyRoom(room_id, room_name, description);
//                                    MainActivity.updateRecyclerViews();
//                                    AccessoriesFragment.updatePagerAdapter();

                                    // TODO: 23.04.2017 fixnout aby nebyly stejny zarizeni ve vsech mistnostech

                                    JSONArray devices = room.getJSONArray("devices");
                                    for (int j = 0; j < devices.length(); j++) {
                                        JSONObject device = devices.getJSONObject(j);

                                        String device_name = device.getString("name");
                                        int device_id = device.getInt("id");
                                        int type_id = device.getInt("type_id");

                                        int device_room_id = device.getInt("room_id"); // double check

                                        if (device_room_id == room_id) {
                                            System.out.println(getRoomIndexByID(room_id));
                                            Home.room(getRoomIndexByID(room_id)).addDevice(device_name, device_id, type_id, -1);
//                                            MainActivity.updateRecyclerViews();
                                        }
                                        else
                                            Toasty.error(context, "Internal error", Toast.LENGTH_SHORT, true).show();

                                    }





                                }


                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                            Toasty.success(context, "Updated successfully", Toast.LENGTH_SHORT, true).show();
                        }

                    }
                });


//        Bridge.get("/keepi/v1/devices")
//                .request(new Callback() {
//                    @Override
//                    public void response(@NonNull Request request, Response response, BridgeException e) {
//
//                    }
//                });

    }


//    public void addRoom(String name, Room room) {
//        roomList.put(name, room);
//    }

    public static int getRoomIDByIndex(int index) {
        return roomList.get(index).id;
    }

    public static int getRoomIndexByID(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            Room room = roomList.get(i);
            if (room.id == id) return i;
        }

        // TODO: 23.04.2017 Bacha na to!
        return 0;
    }

    @Deprecated
    public void removeRoom(String name) {
        roomList.remove(name);
    }

//    public ArrayMap<String, Room> getRoomList() {
//        return roomList;
//    }
//
//    @Deprecated
//    public ArrayList<String> getRoomNames() {
//        ArrayList<String> names = new ArrayList<>();
//        for (int i = 0; i < roomList.size(); i++) names.set(i, roomList.keyAt(i));
//        return names;
//    }


}