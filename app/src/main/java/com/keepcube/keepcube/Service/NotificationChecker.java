package com.keepcube.keepcube.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
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

import es.dmoral.toasty.Toasty;

public class NotificationChecker extends Service {
    public static boolean isRunning;
    public static RequestQueue requestQueue;
    static String TAG = "NotificationChecker";
    final Context context = NotificationChecker.this;
    boolean mAllowRebind; // indicates whether onRebind should be used
    IBinder mBinder;      // interface for clients that bind


    public NotificationChecker() {
    }


    @Override
    public void onCreate() {


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
        }, "NotificationFetchingThread").start();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;

        return Service.START_STICKY;
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
    }


}