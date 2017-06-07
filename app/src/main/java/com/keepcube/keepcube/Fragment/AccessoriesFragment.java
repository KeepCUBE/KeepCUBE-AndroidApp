package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;
import com.keepcube.keepcube.MainActivity;
import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Tools.DataManager.Home;

import net.grandcentrix.tray.AppPreferences;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;


public class AccessoriesFragment extends Fragment {
    public TabLayout tabLayout;
    public ViewPager viewPager;

    public AccessoriesFragment() {
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accessories, container, false);
        AppPreferences prefs = new AppPreferences(getContext());
        final Context context = getContext();

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);


        viewPager.setAdapter(new AccessoriesFragmentPagerAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        // Content Updater, zatím prasácký
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.shouldUpdateRecyclerViews()) {
                    viewPager.setAdapter(new AccessoriesFragmentPagerAdapter(getChildFragmentManager()));
                    MainActivity.recyclerViewsUpdated();
                }
                handler.postDelayed(this, 1);
            }
        }, 1);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addDeviceFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = tabLayout.getSelectedTabPosition();
                Toasty.success(context, String.valueOf(position), Toast.LENGTH_SHORT, false).show();


                final String[] rooms = {"Obývák", "Kuchyň", "Sklep"};
                final String[] deviceTypes = {"LED pásek", "LED světlo", "KeepCUBE Sense"};


                final LayoutInflater inflater = getActivity().getLayoutInflater();

                new AlertDialog.Builder(context)
                        .setTitle("Select room")
                        .setSingleChoiceItems(rooms, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int selectedRoom = which;
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                new AlertDialog.Builder(context)
                                        .setTitle("Select device type")
                                        .setSingleChoiceItems(deviceTypes, 0, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                int selectedDeviceType = which;
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                                new AlertDialog.Builder(context)
                                                        .setTitle("Enter name & unique ID (UID)")
                                                        .setView(inflater.inflate(R.layout.modal_device_add_name, null))
                                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {

                                                            }
                                                        })
                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                // TODO: 22.04.2017 Add device to Home class and send request to PHP Api.
                                                                View view = inflater.inflate(R.layout.modal_device_add_name, null);
                                                                EditText nameEditText = (EditText) view.findViewById(R.id.name);
                                                                EditText uidEditText = (EditText) view.findViewById(R.id.uid);

                                                                String name = nameEditText.getText().toString();
                                                                String uid = uidEditText.getText().toString();

                                                                Toasty.info(context, "updating", Toast.LENGTH_SHORT, true).show();


//                                                                String postContent = "{\"name\":\"AndroidAppTest\",\"type_id\":0,\"room_id\":1}";
//                                                              // {name: String, type_id: int, room_id: int}


                                                                JSONObject postContent = new JSONObject();

                                                                try {
                                                                    postContent.put("name", "AndroidAppTest4");
                                                                    postContent.put("type_id", 0);
                                                                    postContent.put("room_id", 4);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }


                                                                Bridge
                                                                        .post("/keepi/v1/devices")
                                                                        .body(postContent)
                                                                        .header("Accept", "application/json")
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


                                                                                    Toasty.success(context, "Device created", Toast.LENGTH_SHORT, true).show();


                                                                                    Home.updateAccessories(context);
                                                                                    MainActivity.updateRecyclerViews();


                                                                                }

                                                                            }
                                                                        });


//                                                                Volley.newRequestQueue(context).add(new StringRequest(Volley.POST, ))


                                                            }
                                                        })
                                                        .show();
                                            }
                                        })
                                        .show();
                            }
                        })
                        .show();
            }
        });


        return view;
    }


    private class AccessoriesFragmentPagerAdapter extends FragmentPagerAdapter {

        AccessoriesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

/*
        public PageAdapter(FragmentManager fm, ArrayList<Fragment> data) {
            super(fm);
            this.data = data;
        }

  */

        @Override
        public Fragment getItem(int roomPosition) {
            Bundle bundle = new Bundle();
            AccessoriesTabsFragment fragment = new AccessoriesTabsFragment();

            bundle.putInt("roomPosition", roomPosition);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return Home.getNumberOfRooms();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Home.getRoomNameByIndex(position);
        }
    }
}
