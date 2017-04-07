package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        final AppPreferences prefs = new AppPreferences(getContext());
        final Context context = getContext();

        CardView temperature = (CardView) view.findViewById(R.id.tempCard);
        CardView humidity = (CardView) view.findViewById(R.id.humCard);
        CardView pressure = (CardView) view.findViewById(R.id.pressCard);


        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Teplota", Toast.LENGTH_SHORT).show();
            }
        });

        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Vlhkost", Toast.LENGTH_SHORT).show();
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tlak", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
