package com.keepcube.keepcube.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.keepcube.keepcube.Tools.Requester;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

@Deprecated
public class DataManager extends Service {
    public static boolean isRunning;
    public static RequestQueue requestQueue;
    static String TAG = "DataManager";
    static Requester requester = new Requester();
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

        requester.begin(context);

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


//    public Home getHome() {
//        return null;
//    }



//    @Deprecated
//    public static class Accessories {
//
//        static ArrayList<Device> deviceList = new ArrayList<Device>();
//
//
////        void asd() throws JSONException {
////            JSONObject json = new JSONObject();
////            json.put("name", "value");
////        }
//
//        @Deprecated
//        public static void add() {
//
//            requester.addDevice();
//
//            deviceList.add(new Device(Device.LED_STRIP, "Pásek", "Kuchyň"));
//            deviceList.add(new Device(Device.LED_LAMP, "Hlavní světlo", "Kuchyň"));
//            deviceList.add(new Device(Device.KC_SENSE, "Senzory", "Zahrada"));
//
//            Device device = deviceList.get(1);
//
//
//        }
//
//        @Nullable
//        public static Device getDeviceByName(@NonNull String name) {
//            Device device;
//            for (int i = 0; i < getDevicesCount(); i++) {
//                device = deviceList.get(i);
//                if (device.name.equals(name)) {
//                    return device;
//                }
//            }
//            return null;
//        }
//
//        public static int getDevicesCount() {
//            return deviceList.size();
//        }
//
//
//    }








}