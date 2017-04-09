package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabRoomFragment extends Fragment {


    public TabRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_room, container, false);
        AppPreferences prefs = new AppPreferences(getContext());
        Context context = getContext();
        Bundle bundle = getArguments();


        String strtext = bundle.getString("edttext");
        int pos = bundle.getInt("position", -1);



        ((TextView) view.findViewById(R.id.bablbambambam)).setText(strtext + ", position: " + pos);

        return view;
    }

}
