package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Tools.AccessoriesRecyclerAdapter;
import com.keepcube.keepcube.Tools.RoomsRecyclerAdapter;

import net.grandcentrix.tray.AppPreferences;

import java.util.ArrayList;

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


        return view;
    }

}
