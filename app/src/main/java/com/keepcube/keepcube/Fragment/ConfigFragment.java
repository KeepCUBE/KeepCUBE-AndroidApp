package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends Fragment {


    public ConfigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_config, container, false);
        final AppPreferences prefs = new AppPreferences(getContext());
        final Context context = getContext();







        return view;
    }

}
