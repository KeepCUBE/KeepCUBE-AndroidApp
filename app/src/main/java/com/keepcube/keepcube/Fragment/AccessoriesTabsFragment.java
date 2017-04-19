package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Tools.AccessoriesRecyclerAdapter;

import net.grandcentrix.tray.AppPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccessoriesTabsFragment extends Fragment {


    public AccessoriesTabsFragment() {
        // Required empty public constructor
    }

//    public AccessoriesTabsFragment(Home home) {
//        this.home = home;
//        // Required empty public constructor
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_room, container, false);
        AppPreferences prefs = new AppPreferences(getContext());
        Context context = getContext();
        Bundle bundle = getArguments();

        int roomPosition = bundle.getInt("roomPosition", -1);


//        ArrayList<String> names = new ArrayList<String>();
//        ArrayList<Integer> numberOfDevices = new ArrayList<Integer>();
//
//        names.add("1");
//        names.add("2");
//        names.add("3");
//        names.add("4");
//
//        numberOfDevices.add(2);
//        numberOfDevices.add(4);
//        numberOfDevices.add(1);
//        numberOfDevices.add(8);

        // TODO: 18.04.2017 presunout do DataManageru
//        ArrayList<DataManager.Room> rooms = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//
//            DataManager.Room room = new DataManager.Room("Kuchyň");
//
//            for (int j = 0; j < 10; j++) {
//                // TODO: 18.04.2017 udelat ziskavani dat
//                room.addDevice(new DataManager.Device(DataManager.Device.LED_LAMP, "Hlavní světlo", "Kuchyň"));
//            }
//
//            rooms.add(room);
//        }


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.accessoriesRecycler);
        recyclerView.setHasFixedSize(false); // prej zlepší výkon, jde o layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AccessoriesRecyclerAdapter(roomPosition));





        return view;
    }

}
