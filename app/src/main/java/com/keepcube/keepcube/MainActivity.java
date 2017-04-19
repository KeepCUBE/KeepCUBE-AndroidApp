package com.keepcube.keepcube;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.keepcube.keepcube.Fragment.AccessoriesFragment;
import com.keepcube.keepcube.Fragment.ConfigFragment;
import com.keepcube.keepcube.Fragment.HomeFragment;
import com.keepcube.keepcube.Fragment.RoomsFragment;
import com.keepcube.keepcube.Tools.DataManager.Device;
import com.keepcube.keepcube.Tools.DataManager.Home;

import net.grandcentrix.tray.AppPreferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static boolean updateRecyclerViews = false;
    FragmentManager fragmentManager = getSupportFragmentManager();
    HomeFragment homeFragment = new HomeFragment();
    RoomsFragment roomsFragment = new RoomsFragment();
    ConfigFragment configFragment = new ConfigFragment();
    AccessoriesFragment accessoriesFragment = new AccessoriesFragment();
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppPreferences prefs = new AppPreferences(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Custom
        toolbar.setTitle("KeepCUBE");
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        // TODO: 20.03.2017 udelat aby se po spusteni zobrazil posledni navstiveny fragment.

//        final Intent intent = new Intent(getApplicationContext(), DataManager.class);
//        getApplicationContext().startService(intent);


        Home.update(getApplicationContext());

        Home.addEmptyRoom("obejvak");
        Home.addEmptyRoom("kuchyn");
        Home.addEmptyRoom("sklep");

        Home.room("obejvak").addDevice(Device.LED_STRIP, "ledky2");
        Home.room("obejvak").addDevice(Device.LED_STRIP, "ledky3");
        Home.room("obejvak").addDevice(Device.LED_STRIP, "ledky4");

        Home.room("kuchyn").addDevice(Device.LED_STRIP, "Mixér");

        Home.room("sklep").addDevice(Device.LED_STRIP, "ledky5");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        MenuItem item = menu.findItem(R.id.action_update);
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(getApplicationContext(), R.color.white));
        item.setIcon(wrapDrawable);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // TODO: 06.04.2017 Settings Activity!
//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
////            intent.putExtra("key", value); //Optional parameters
//            MainActivity.this.startActivity(intent);
//            Toast.makeText(this, "onOptionsItemSelected()", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_update) {
            Home.update(getApplicationContext());

            Home.addEmptyRoom("obejvak");
            Home.addEmptyRoom("kuchyn");
            Home.addEmptyRoom("sklep");

            Home.room("obejvak").addDevice(Device.LED_STRIP, "ledky2");
            Home.room("obejvak").addDevice(Device.LED_STRIP, "ledky3");
            Home.room("kuchyn").addDevice(Device.LED_STRIP, "Mixér");
            Home.room("sklep").addDevice(Device.LED_STRIP, "ledky5");

            updateRecyclerViews = true;

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                toolbar.setTitle("KeepCUBE Dashboard");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                break;

            case R.id.nav_accessories:
                toolbar.setTitle("Accessories");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, accessoriesFragment).commit();
                break;

            case R.id.nav_rooms:
                toolbar.setTitle("Místnosti");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, roomsFragment).commit();
                break;

            case R.id.nav_conf:
                toolbar.setTitle("Config");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, configFragment).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
