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

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.accessoriesRecycler);
        recyclerView.setHasFixedSize(false); // prej zlepší výkon, jde o layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AccessoriesRecyclerAdapter(roomPosition));





        return view;
    }

}
