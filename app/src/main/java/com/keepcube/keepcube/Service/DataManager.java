package com.keepcube.keepcube.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
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

public class DataManager extends Service {
    public static boolean isRunning;
    public static RequestQueue requestQueue;
    static String TAG = "DataManager";
    final Context context = DataManager.this;
    boolean mAllowRebind; // indicates whether onRebind should be used
    IBinder mBinder;      // interface for clients that bind


    public DataManager() {
    }


    public static boolean update(final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                requestQueue.add(new StringRequest(Request.Method.GET, "http://httpstat.us/200",
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
                        Looper.prepare();
                        Toasty.info(context, "HTTP Status code: " + String.valueOf(response.statusCode), Toast.LENGTH_SHORT, true).show();

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

        return false;
    }

    @Override
    public void onCreate() {
        requestQueue = Volley.newRequestQueue(context);

        update(context);

        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                Log.d(TAG, "Request Finished!");
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;

//        return Service.START_STICKY;
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        isRunning = false;
        Toast.makeText(context, "Proces byl ukončen!", Toast.LENGTH_SHORT).show();
    }


    public static class Users {
        static ArrayList<String> namesList = new ArrayList<String>();
        static ArrayList<String> ipList = new ArrayList<String>();
        static ArrayList<String> macList = new ArrayList<String>();


        public static boolean add(String name, String ip, String mac) {
            if (namesList.contains(name)) return false;

            namesList.add(name);
            ipList.add(ip);
            macList.add(mac);
            // TODO: 17.04.2017 Btw, co jen poslat request a updatnout to? :D

            return true;
        }

        public static String getName(int index) {
            return namesList.get(index);
        }

        public static String getIP(int index) {
            return ipList.get(index);
        }

        public static String getMAC(int index) {
            return macList.get(index);
        }

        public static int getIndexByName(String name) {
            return namesList.lastIndexOf(name);
        }

        public static int getIndexByIP(String ip) {
            return ipList.lastIndexOf(ip);
        }

        public static int getIndexByMAC(String mac) {
            return macList.lastIndexOf(mac);
        }

        public static int getUsersCount() {
            int x = namesList.size();
            int y = ipList.size();
            int z = macList.size();

            if (x == y && y == z) {
                return x;
            } else return -1;
        }

        @Deprecated
        public static void remove(String name) {
            // TODO: 17.04.2017 udelat aby to vracelo bool o tom, jestli se odebral uspesne
            int index = namesList.lastIndexOf(name);

            namesList.remove(index);
            ipList.remove(index);
            macList.remove(index);
        }


    }


    public static class Accessories {

        static ArrayList<Device> deviceList = new ArrayList<Device>();


//        void asd() throws JSONException {
//            JSONObject json = new JSONObject();
//            json.put("name", "value");
//        }

        public static void add() {

            deviceList.add(new Device(Device.LED_STRIP, "Pásek", "Kuchyň"));
            deviceList.add(new Device(Device.LED_LAMP, "Hlavní světlo", "Kuchyň"));
            deviceList.add(new Device(Device.KC_SENSE, "Senzory", "Zahrada"));

            Device device = deviceList.get(0);

            Log.d("iiiiiiiiiiiiiiiiiiiiiii", device.name);


        }

        public static Device getDeviceByName(String name) {
            Device device;
            for (int i = 0; i < getDevicesCount(); i++) {
                device = deviceList.get(i);
                if (device.name.equals(name)) {
                    return device;
                }
            }
            return null;
        }


        public static int getDevicesCount() {
            return deviceList.size();
        }


        static class Device {
            // Constants
            static int LED_STRIP = 1;
            static int LED_LAMP = 2;
            static int KC_SENSE = 3;

            int type = -1;
            String name = "";
            String room  = "";

            /**
             * @param type type of device
             * @param name name of device
             * @param room name of room where device is
             */
            Device(int type, String name, String room) {
                this.type = type;
                this.name = name;
                this.room = room;
            }

            void asd() {
            }


        }


    }


}