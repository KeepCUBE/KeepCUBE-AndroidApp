package com.keepcube.keepcube.Tools;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ondrej on 17.04.2017.
 */

public class Requester {
    Context context;
    RequestQueue requestQueue;

    Requester(Context c) {
        context = c;
        requestQueue = Volley.newRequestQueue(context);

    }



    void addUser() {



    }
//
//
//    Bundle getUsers() {
//        Bundle bundle = new Bundle();
//
//
//
//
//
//
//
//        bundle.putStringArrayList();
//
//        return bundle;
//    }


}
