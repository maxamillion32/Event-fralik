package com.test.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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

import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnEventSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    public Location lastLocation;
    private LocationManager locationManager;
    public static int ACCESS_LOCATION_REQUEST_CODE = 323;
    public double latitude, longitude;

    private static final String API_KEY_EVENT_BRITE = "AMDMMKWPWFPOCAUYVIW2";
    public static final String CALL_ENQUE_ALL_EVENTS_KEY = "AllEvents";
    private String TAG = "MainActivity";

    private FragmentPagerAdapter adapterViewPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;



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

        locationServiceStatusCheck();

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
//
//    public void statusCheck()
//    {
//        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
//
//        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
//            buildAlertMessageNoGps();
//
//        }
//
//
//    }
//    private void buildAlertMessageNoGps() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog,  final int id) {
//                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, final int id) {
//                        dialog.cancel();
//                    }
//                });
//        final AlertDialog alert = builder.create();
//        alert.show();
//
//    }

    public void locationServiceStatusCheck() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            alertDialogForLocation();

        }


    }

    public void alertDialogForLocation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
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

//        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
//        }

        Log.i(TAG, "onConnected: ");

        Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show();

//        if(ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
//                    ACCESS_LOCATION_REQUEST_CODE);
//
//        }

//        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);






//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        if (mLastLocation != null) {
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: ");
        Toast.makeText(this,"No GPS connection",Toast.LENGTH_SHORT).show();
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
            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();

            Log.i(TAG, "saveLocation: lat = "+lastLocation.getLatitude());
            Log.i(TAG, "saveLocation: long = "+lastLocation.getLongitude());


        } else {
            Log.i(TAG, "saveLocation: lastlocation is null ");
        }

//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
////                mGoogleApiClient);
////        if (mLastLocation != null) {
////            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
////            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
////        }


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

        Log.i(TAG, "onEventSelected: ");

        Log.i(TAG, "onEventSelected: description is "+selectedEvent.getDescription().getText());
        Log.i(TAG, "onEventSelected: title is "+selectedEvent.getName().getText());

        Intent intent = new Intent(this, DetailActivity.class);
        Log.i(TAG, "onEventSelected: new intent made");
        
        intent.putExtra("description",selectedEvent.getDescription().getText());
        intent.putExtra("title",selectedEvent.getName().getText());
        intent.putExtra("where",selectedEvent.getStart().getTimezone());
        intent.putExtra("when",selectedEvent.getStart().getLocal());
        intent.putExtra("category",selectedEvent.getCategoryId());

        if(selectedEvent.getLogo() != null) {
            intent.putExtra("image", selectedEvent.getLogo().getUrl());
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


    private void googleAPIClientSetup() {

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* AppCompatActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

    }

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
        AppEventsLogger.activateApp(getApplication(), "1813279535570044");

    }
    //endregion

    //region facebook login
    private void facebookLogin() {

        loginButton = (LoginButton) findViewById(R.id.login_button);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginButton.setVisibility(View.GONE);

                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }
    // endregion

    //region retrofit call (Free Events)

    public void loadFreeEvents() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && !networkInfo.isConnected()){
            Toast.makeText(MainActivity.this, R.string.toast_no_network, Toast.LENGTH_SHORT).show();
        }

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "loadFreeEvents: retrofit object created");

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> call = request.getAllFreeEvents("free","Bearer "+ API_KEY_EVENT_BRITE);

        call.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    Log.i(TAG, "onResponse: start --------------------");

//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: freeEventsObject "+response.body());

                    ArrayList<Event> data = new ArrayList<Event>();

                    Log.i(TAG, "onResponse: initial data size = "+data.size());

                    data.addAll(freeEventsObject.getEvents());

                    Log.i(TAG, "onResponse: ArrayList Data Size after adding = "+data.size());

                    Log.i(TAG, "onResponse: data = "+data);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(CALL_ENQUE_ALL_EVENTS_KEY,data);

                    EventsRecyclerViewFragment eventsRvFragment = new EventsRecyclerViewFragment();
                    eventsRvFragment.setArguments(bundle);

                    Log.i(TAG, "onResponse: end ---------------------");

//                    EventsRecyclerViewFragment.newInstance(position)


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {

                Log.i(TAG, "onFailure: ");

            }
        });
    }

    //endregion / //


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
        private  int TAB_COUNT = 5;

        private String tabTitles[] = new String[] { "EVENTS", "CATEGORIES", "Tab3", "Tab4", "Tab5" };
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

//                    MainActivity.this.loadFreeEvents();

//                    Log.i(TAG, "getItem: loadFreeEvents call completed");

                    return EventsRecyclerViewFragment.newInstance(position);

                // region case 1,2,3
                case 1:
                    return FirstFragment.newInstance(position+1);

                case 2:
                    return FirstFragment.newInstance(position+1);

                case 3:
                    return FirstFragment.newInstance(position+1);

                case 4:
                    return FirstFragment.newInstance(position+1);

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
