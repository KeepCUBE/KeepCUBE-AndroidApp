package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.bridge.Bridge;
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

//        EditText url = (EditText) view.findViewById(R.id.urlEditText);
//
//
//        url.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Bridge.config().host(String.valueOf(s));
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Bridge.config().host(String.valueOf(s));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Bridge.config().host(String.valueOf(s));
//            }
//        });






        return view;
    }

}
