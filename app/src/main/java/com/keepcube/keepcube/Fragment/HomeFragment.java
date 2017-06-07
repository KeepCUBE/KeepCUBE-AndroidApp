package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Service.NotificationChecker;

import net.grandcentrix.tray.AppPreferences;

import es.dmoral.toasty.Toasty;

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
        Switch notificationCheckerSwitch = (Switch) view.findViewById(R.id.notificationCheckerSwitch);

        final Intent NotificationCheckerIntent = new Intent(context, NotificationChecker.class);


        notificationCheckerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    context.stopService(NotificationCheckerIntent);
                    context.startService(NotificationCheckerIntent);
                    Toasty.info(context, "Starting Notification Checker", Toast.LENGTH_SHORT, true).show();
                } else {
                    context.stopService(NotificationCheckerIntent);
                    Toasty.info(context, "Stopping Notification Checker", Toast.LENGTH_SHORT, true).show();
                }
            }
        });


//        DataManager.Users.add("Melichar", "192.168.0.255", "00:11:22:33:44:55");
//        DataManager.Users.add("Honimír", "192.168.0.88", "AA:BB:CC:DD:EE:FF");
//        DataManager.Users.add("Ohnislav", "192.168.0.128", "66:66:66:66:66:66");
//        DataManager.Users.add("Trautumberg", "192.168.0.12", "32:32:32:32:32:32");
//        DataManager.Users.add("Mánička", "192.168.0.33", "88:44:88:44:88:44");


//        DataManager.Accessories.add();


//        String html = "";
//        for (int i = 0; i < DataManager.Users.getUsersCount(); i++) {
//
//            String name = DataManager.Users.getName(i);
//            String ip = DataManager.Users.getIP(i);
//            String mac = DataManager.Users.getMAC(i);
//
//            html = html.concat(String.format("<b>%s</b>,&ensp;%s,&ensp;<i>%s</i><br>", name, ip, mac));
//        }

//        html = html.substring(0, html.length() - 4); // remove <br> at the end
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            usersField.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            usersField.setText(Html.fromHtml(html));
//        }


        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Teplota", Toast.LENGTH_SHORT).show();
//                Toasty.error(context, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
            }
        });

        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.success(context, "Success!", Toast.LENGTH_SHORT, true).show();
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.info(context, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
            }
        });


        return view;
    }

}
