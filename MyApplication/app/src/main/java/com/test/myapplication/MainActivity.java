package com.test.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.test.myapplication.APIService.EventBriteAPIService;
import com.test.myapplication.Activity.DetailActivity;
import com.test.myapplication.Activity.LoginActivity;
import com.test.myapplication.Fragment.EventsRecyclerViewFragment;
import com.test.myapplication.Fragment.FirstFragment;
import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnEventSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    public Location lastLocation;
    private LocationManager locationManager;
    public static int ACCESS_LOCATION_REQUEST_CODE = 323;
    public String latitude, longitude;

    private static final String API_KEY_EVENT_BRITE = "AMDMMKWPWFPOCAUYVIW2";
    public static final String CALL_ENQUE_ALL_EVENTS_KEY = "AllEvents";
    private String TAG = "MainActivity";

    private FragmentPagerAdapter adapterViewPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
//
//    FragmentManager fragmentManager;
//    FragmentTransaction fragmentTransaction;
//


    CallbackManager callbackManager;

    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleAPIClientSetup();

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

        Log.i(TAG, "onCreate: ");

//        locationServiceStatusCheck();

        setupToolbarAndDrawer();

        setupViewPagerAndTabs();

        facebookInitialization();

        //facebookLogin();


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
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    public void locationServiceStatusCheck() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            alertDialogForLocation();

        }
    }

    public void alertDialogForLocation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.alert_dialog_message_gps_disabled)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected: ");

        saveLocation();

        Log.i(TAG, "onConnected: ");

        Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: ");
        Toast.makeText(this, R.string.toast_msg_no_connection,Toast.LENGTH_SHORT).show();
    }

    private void saveLocation() {
        // Requesting location permission if we don't have it

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            return;

        }

        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        
        if(lastLocation != null) {

            latitude = String.valueOf(lastLocation.getLatitude());
            longitude = String.valueOf(lastLocation.getLongitude());

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.key_location_services_latitude), latitude);
            editor.putString(getString(R.string.key_location_services_longitude),longitude);
            editor.commit();

        } else {
            Log.i(TAG, "saveLocation: lastlocation is null ");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                saveLocation();

            } else {
             //permission denied !
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onEventSelected(Event selectedEvent) {

//        android.R.style.Theme_Holo_Dialog_NoActionBar

        Log.i(TAG, "onEventSelected: ");

        Log.i(TAG, "onEventSelected: description is "+selectedEvent.getDescription().getText());
        Log.i(TAG, "onEventSelected: title is "+selectedEvent.getName().getText());

        Intent intent = new Intent(this, DetailActivity.class);
        Log.i(TAG, "onEventSelected: new intent made");
        
        intent.putExtra(getString(R.string.key_event_description),selectedEvent.getDescription().getText());
        intent.putExtra(getString(R.string.key_event_title),selectedEvent.getName().getText());
        intent.putExtra(getString(R.string.key_event_city),selectedEvent.getVenue().getAddress().getCity());
        intent.putExtra(getString(R.string.key_event_time_date),selectedEvent.getStart().getLocal());

        if(selectedEvent.getCategory().getShortNameLocalized() != null ) {
            intent.putExtra(getString(R.string.key_event_category), selectedEvent.getCategory().getShortNameLocalized());
        }

        intent.putExtra(getString(R.string.key_event_price),selectedEvent.getTicketClasses().getClass());
        intent.putExtra(getString(R.string.key_event_full_address),selectedEvent.getVenue().getAddress().getLocalizedAddressDisplay());
        intent.putExtra(getString(R.string.key_event_url),selectedEvent.getUrl());



        if(selectedEvent.getLogo() != null) {
            intent.putExtra(getString(R.string.key_event_image), selectedEvent.getLogo().getUrl());
        }
//        intent.putExtra("selectedEvent",selectedEvent);
        startActivity(intent);

    }

    // region Toolbar Items Click method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

    // region Navigation Drawer Clicks method

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // endregion

    // region GoogleAPIClient Setup
    private void googleAPIClientSetup() {

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* AppCompatActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }
    // endregion

    // region Toolbar and Drawer setup method
    private void setupToolbarAndDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //endregion

    //region Facebook Initialization

    private void facebookInitialization() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication(), getString(R.string.facebook_app_id));

    }
    //endregion

    //region facebook login
//    private void facebookLogin() {
//
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                loginButton.setVisibility(View.GONE);
//
//                tabLayout.setVisibility(View.VISIBLE);
//                viewPager.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//
//    }
    // endregion

    //region VIEW PAGER CODE

    //region View Pager setup method

    private void setupViewPagerAndTabs() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        viewPager.setAdapter(adapterViewPager);

//        viewPager.setVisibility(View.INVISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.setVisibility(View.INVISIBLE);

    }

    //endregion

    // region Inner Class MyPagerAdapter for View Pager
    private class MyPagerAdapter extends FragmentPagerAdapter {

        // region pager setup
        private  int TAB_COUNT = 10;

        private String tabTitles[] = new String[] {
                getString(R.string.event_type_all_events),
                getString(R.string.event_type_music),
                getString(R.string.event_type_food_drink),
                getString(R.string.event_type_entertainment),
                getString(R.string.event_type_outdoor),
                getString(R.string.event_type_sports),
                getString(R.string.event_type_hobbies),
                getString(R.string.event_type_air_boat),
                getString(R.string.event_type_health),
                getString(R.string.event_type_arts)
        };

        private Context context;

        public MyPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
        //endregion

        @Override
        public Fragment getItem(int position) {
//            return FirstFragment.newInstance(position + 1);

            switch (position) {

                case 0:

                    Log.i(TAG, "getItem: switch case0");

//                    Log.i(TAG, "getItem: loadFreeEvents call completed");

                    return EventsRecyclerViewFragment.newInstance(position,getString(R.string.event_type_all_events));

                // region case 1,2,3
                case 1:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_music));

                case 2:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_food_drink));

                case 3:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_entertainment));

                case 4:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_outdoor));

                case 5:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_sports));

                case 6:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_hobbies));

                case 7:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_air_boat));

                case 8:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_health));

                case 9:
                    return EventsRecyclerViewFragment.newInstance(position+1,getString(R.string.event_type_arts));

                //endregion

            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return tabTitles[position];
        }
    }

    //endregion

    // endregion

}
