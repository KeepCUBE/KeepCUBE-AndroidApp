package com.keepcube.keepcube.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepcube.keepcube.R;

import net.grandcentrix.tray.AppPreferences;


public class AccessoriesFragment extends Fragment {
    public TabLayout tabLayout;
    public ViewPager viewPager;

    public AccessoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accessories, container, false);
        AppPreferences prefs = new AppPreferences(getContext());
        Context context = getContext();

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new AccessoriesFragmentPagerAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
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
            bundle.putInt("position", position);
            bundle.putString("edttext", "Bundle test");
            TabRoomFragment fragobj = new TabRoomFragment();
            fragobj.setArguments(bundle);

            return fragobj;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Obývák";
                case 1:
                    return "Sklep";
                case 2:
                    return "Kuchyň";
                case 3:
                    return "Ložnice";
                case 4:
                    return "Koupelna";
                case 5:
                    return "Zahrada";
                case 6:
                    return "Garáž";
                case 7:
                    return "Půda";
            }
            return null;
        }
    }
}
