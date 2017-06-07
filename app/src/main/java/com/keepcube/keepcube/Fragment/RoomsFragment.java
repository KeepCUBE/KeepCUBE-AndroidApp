package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;
import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Tools.DataManager.Home;
import com.keepcube.keepcube.Tools.RoomsRecyclerAdapter;

import net.grandcentrix.tray.AppPreferences;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment {


    public RoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        final AppPreferences prefs = new AppPreferences(getContext());
        final Context context = getContext();


        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> numberOfDevices = new ArrayList<Integer>();

        names.add("Obejvák");
        names.add("Sklep");
        names.add("Kuchyň");
        names.add("Ložnice");

        numberOfDevices.add(2);
        numberOfDevices.add(4);
        numberOfDevices.add(1);
        numberOfDevices.add(8);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true); // prej zlepší výkon, jde o layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RoomsRecyclerAdapter(names, numberOfDevices));




        JSONObject postContent = new JSONObject();

        try {
//            postContent.put("id", "AndroidAppTest2");
            postContent.put("name", "Sklep");
            postContent.put("description", "popispopispopis");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Bridge
                .post("/keepi/v1/rooms")
                .body(postContent)
                .request(new Callback() {
                    @Override
                    public void response(@NotNull Request request, @Nullable Response response, @Nullable BridgeException e) {

                        if (e != null) {
                            // See the 'Error Handling' section for information on how to process BridgeExceptions
                            int reason = e.reason();
                            System.out.println(String.valueOf(reason));
                            Toasty.error(context, "Base unreachable", Toast.LENGTH_SHORT, true).show();

                        } else {
                            String res = response.asString();
                            System.out.println(res);

                            Toasty.success(context, "Room created", Toast.LENGTH_SHORT, true).show();

                            Home.updateAccessories(context);

                        }

                    }
                });


        return view;
    }

}
