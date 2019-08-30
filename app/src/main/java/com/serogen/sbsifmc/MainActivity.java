package com.serogen.sbsifmc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    private static final String TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT";

    public static final int ID_GROUP_ALL = 0;
    public static final int ID_GROUP_HOUSES = 1;
    public static final int ID_GROUP_MEDIEVAL_HOUSES = 2;
    public static final int ID_GROUP_FOUNTAINS = 3;
    public static final int ID_GROUP_OUTDOOR_DECORATIONS = 4;
    public static final int ID_GROUP_HOUSE_DECORATIONS = 5;
    public static final int ID_GROUP_OTHER = 6;

    final int adFrequency = 6;
    int adCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String appKey = "93aef72b421788b5f55095fd26319c22b6ddca464840a628";
        Appodeal.setBannerViewId(R.id.appodealBannerView);
        Appodeal.setAutoCache(Appodeal.BANNER_VIEW,true);
        Appodeal.initialize(this, appKey, Appodeal.BANNER | Appodeal.INTERSTITIAL);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            MainFragment homeFragment = MainFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.content_frame,homeFragment, TAG_HOME_FRAGMENT)
                    .commit();
            Appodeal.show(this, Appodeal.BANNER_VIEW);
        }

        if (!isOnline()) {
            try {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getString(R.string.internet_connection_error_title));
                alertDialog.setMessage(getString(R.string.internet_connection_error));
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setPositiveButton(getString(R.string.close_app),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                alertDialog.setCancelable(false);
                alertDialog.create().show();
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER_VIEW);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
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
            String url = "https://www.appodeal.com/home/privacy-policy/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.action_site) {
            String url = "https://instructions-for-minecraft.000webhostapp.com/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.menu_home) {
            fragmentClass = MainFragment.class;
        } else if (id == R.id.menu_tutorials) {
            fragmentClass = InstructionsListFragment.class;
        } else if (id == R.id.menu_categories) {
            fragmentClass = GroupsFragment.class;
        } else if (id == R.id.menu_about) {
            String version = "Unknown";
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                version = pInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
            alertDialogBuilderUserInput
                    .setCancelable(true)
                    .setTitle(getString(R.string.about))
                    .setMessage(getString(R.string.version,version))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {

                        }

                    });

            final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
            alertDialog.show();
        }
        if (id != R.id.menu_about) {
            try {
                if (fragmentClass == InstructionsListFragment.class)
                    fragment = InstructionsListFragment.newInstance(ID_GROUP_ALL);
                else
                    fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            setTitle(item.getTitle());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean mobileDisabled = false;
        try {
            mobileDisabled = mobileInfo.getState() == NetworkInfo.State.DISCONNECTED
                    && (mobileInfo.getReason() == null || mobileInfo.getReason().equals("specificDisabled"));
        } catch (Exception e){}


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        boolean wifiEnabled = wifiManager.isWifiEnabled();

        return !mobileDisabled || wifiEnabled;
    }


    public void showAd() {
        if (Appodeal.isLoaded(Appodeal.INTERSTITIAL))
            Appodeal.show(this,Appodeal.INTERSTITIAL);
    }

    public void showCountAd() {
        if (Appodeal.isLoaded(Appodeal.INTERSTITIAL) && adCounter >= adFrequency) {
            Appodeal.show(this, Appodeal.INTERSTITIAL);
            adCounter = 0;
        } else {
            increaseAdCount();
        }
    }

    public void increaseAdCount() {
        adCounter++;
    }


    public void onInstructionItemClicked(int instructionId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, InstructionFragment.newInstance(instructionId))
                .addToBackStack("insts")
                .commit();
    }
    public void onGroupsListClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new GroupsFragment())
                .addToBackStack("groups_list")
                .commit();
    }

    public void onGroupClicked(int groupId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, InstructionsListFragment.newInstance(groupId))
                .addToBackStack("group")
                .commit();
    }
    public void setWindowTitle(String title) {
        setTitle(title);
    }
}
