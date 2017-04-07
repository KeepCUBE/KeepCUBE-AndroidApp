package com.keepcube.keepcube;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.keepcube.keepcube.Fragment.ConfigFragment;
import com.keepcube.keepcube.Fragment.HomeFragment;
import com.keepcube.keepcube.Fragment.RoomsFragment;

import net.grandcentrix.tray.AppPreferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

    HomeFragment homeFragment = new HomeFragment();
    RoomsFragment roomsFragment = new RoomsFragment();
    ConfigFragment configFragment = new ConfigFragment();

    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppPreferences prefs = new AppPreferences(this);





        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Custom
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        // TODO: 20.03.2017 udelat aby se po spusteni zobrazil posledni navstiveny fragment.




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
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // TODO: 06.04.2017 Settings Activity!
//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
////            intent.putExtra("key", value); //Optional parameters
//            MainActivity.this.startActivity(intent);
//
//
//            Toast.makeText(this, "onOptionsItemSelected()", Toast.LENGTH_SHORT).show();
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

            case R.id.nav_rooms:
                toolbar.setTitle("Místnosti");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, roomsFragment).commit();
                break;





            case R.id.nav_conf:
                toolbar.setTitle("Config");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, configFragment).commit();
                break;





//
//            case R.id.nav_chat:
//                toolbar.setTitle("Třídní chat");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, chatFragment).commit();
//                break;
//
//            case R.id.nav_hlaska:
//                toolbar.setTitle("Hláška minulého týdne");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, hlaskaFragment).commit();
//                break;
//
//            case R.id.nav_supl:
//                toolbar.setTitle("Suplování");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, suplFragment).commit();
//                break;
//
//            case R.id.nav_news:
//                toolbar.setTitle("Aktuality");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, newsFragment).commit();
//                break;
//
//            case R.id.nav_access:
//                toolbar.setTitle("Přístupový čip");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, accessFragment).commit();
//                break;
//
//            case R.id.nav_menu:
//                toolbar.setTitle("Jídelníček");
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, menuFragment).commit();
//                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
