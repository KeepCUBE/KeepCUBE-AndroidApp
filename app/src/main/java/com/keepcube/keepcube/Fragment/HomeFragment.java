package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;

import java.util.ArrayList;

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

        TextView usersField = (TextView) view.findViewById(R.id.usersTextView);


        ArrayList<String> users = new ArrayList<String>();
        ArrayList<String> macAddresses = new ArrayList<String>();
        ArrayList<String> ipAddresses = new ArrayList<String>();


        users.add("Melichar");
        users.add("Honimír");
        users.add("Ohnislav");
        users.add("Trautumberg");
        users.add("Mánička");

        macAddresses.add("00:11:22:33:44:55");
        macAddresses.add("AA:BB:CC:DD:EE:FF");
        macAddresses.add("66:66:66:66:66:66");
        macAddresses.add("32:32:32:32:32:32");
        macAddresses.add("88:44:88:44:88:44");

        ipAddresses.add("192.168.0.255");
        ipAddresses.add("192.168.0.88");
        ipAddresses.add("192.168.0.128");
        ipAddresses.add("192.168.0.12");
        ipAddresses.add("192.168.0.33");

        String html = "";

        for (int i = 0; i < users.size(); i++)
            html = html.concat(String.format("<b>%s</b>,&ensp;%s,&ensp;<i>%s</i><br>", users.get(i), ipAddresses.get(i), macAddresses.get(i)));

        html = html.substring(0, html.length() - 4);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            usersField.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            usersField.setText(Html.fromHtml(html));
        }





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
