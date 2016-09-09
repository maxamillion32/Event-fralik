package com.test.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.myapplication.R;

/**
 * Created by NehaRege on 9/8/16.
 */
public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    public String eventLatitude, eventLongitude, eventAddress, eventImage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        latitude = sharedPreferences.getString(getString(R.string.key_location_services_latitude),null);
//        longitude = sharedPreferences.getString(getString(R.string.key_location_services_longitude),null);

        Intent intent = getIntent();
        eventLatitude = intent.getStringExtra(getString(R.string.key_event_latitude));
        eventLongitude = intent.getStringExtra(getString(R.string.key_event_longitude));
        eventAddress = intent.getStringExtra(getString(R.string.key_event_short_address));

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {



        LatLngBounds latLngBounds = new LatLngBounds(
                new LatLng(Double.parseDouble(eventLatitude),Double.parseDouble(eventLongitude)),
                new LatLng(Double.parseDouble(eventLatitude),Double.parseDouble(eventLongitude))
        );

        // Move the camera instantly to Sydney with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(
                        new LatLng(Double.parseDouble(eventLatitude),Double.parseDouble(eventLongitude)), 15)
        );

// Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(Double.parseDouble(eventLatitude),Double.parseDouble(eventLongitude)))      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(eventLatitude),Double.parseDouble(eventLongitude)))
                .title(eventAddress)
//                .snippet(eventAddress)
        );

//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBounds.getCenter(),15));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

