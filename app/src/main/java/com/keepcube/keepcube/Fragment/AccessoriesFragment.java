package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;

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


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addDeviceFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = tabLayout.getSelectedTabPosition();
                Toasty.success(context, String.valueOf(position), Toast.LENGTH_SHORT, false).show();
            }
        });

        return view;
    }


    private class AccessoriesFragmentPagerAdapter extends FragmentPagerAdapter {

        AccessoriesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            TabRoomFragment fragment = new TabRoomFragment();

            bundle.putInt("position", position);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Obývák";
                case 1:
                    return "Kuchyň";
                case 2:
                    return "Ložnice";
                case 3:
                    return "Koupelna";
                case 4:
                    return "Půda";
            }
            return "null";
        }
    }
}
