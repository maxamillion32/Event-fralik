package com.test.myapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.test.myapplication.R;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

/**
 * Created by NehaRege on 8/22/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String TAG="DetailActivity";

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewWhen;
    private TextView textViewAdd1;
    private TextView textViewCategory;

    private FloatingActionMenu fam;
    private FloatingActionButton fab1, fab2, fab3;

    private Button locationButton;

//    private MapView mMapView;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    MessageDialog messageDialog;

    public String latitude, longitude;
    public String eventLatitude, eventLongitude;

//    MapView mapView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE
//
//
//        );

        setContentView(R.layout.activity_detail);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        latitude = sharedPreferences.getString(getString(R.string.key_location_services_latitude),null);
        longitude = sharedPreferences.getString(getString(R.string.key_location_services_longitude),null);



        initializeFacebookStuff();

        initializeViews();

        SessionConfiguration configuration = new SessionConfiguration.Builder()
                .setClientId("YOUR_CLIENT_ID") //This is necessary
                .setRedirectUri("YOUR_REDIRECT_URI") //This is necessary if you'll be using implicit grant
                .setEnvironment(SessionConfiguration.Environment.SANDBOX) //Useful for testing your app in the sandbox environment
                .setScopes(Arrays.asList(Scope.PROFILE,Scope.RIDE_WIDGETS))
                .build();

        UberSdk.initialize(configuration);

        final Intent intent = getIntent();

        eventLatitude = intent.getStringExtra(getString(R.string.key_event_latitude));
        eventLongitude = intent.getStringExtra(getString(R.string.key_event_longitude));

        setViews(intent);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(intent.getStringExtra(getString(R.string.key_event_url))!=null) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra(getString(R.string.key_event_url)));
                    startActivity(Intent.createChooser(shareIntent, "Share"));
                }

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(intent.getStringExtra(getString(R.string.key_event_url))!=null &
                        intent.getStringExtra(getString(R.string.key_event_title))!=null) {

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(intent.getStringExtra(getString(R.string.key_event_title)))
                            .setContentUrl(Uri.parse(intent.getStringExtra(getString(R.string.key_event_url))))
                            .build();

                    shareDialog.show(linkContent);
                }

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MessageDialog.canShow(ShareLinkContent.class)) {

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(intent.getStringExtra(getString(R.string.key_event_title)))
                            .setContentDescription("description"/*intent.getStringExtra(getString(R.string.key_event_description))*/)
                            .setContentUrl(Uri.parse(getString(R.string.key_event_url)))
                            .build();

                    messageDialog.show(linkContent);

                }
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DetailActivity.this,LocationActivity.class);
                intent1.putExtra(getString(R.string.key_event_latitude),eventLatitude);
                intent1.putExtra(getString(R.string.key_event_longitude),eventLongitude);
                intent1.putExtra(getString(R.string.key_event_short_address),
                        intent.getStringExtra(getString(R.string.key_event_short_address)));
                startActivity(intent1);


            }
        });


//        RideRequestButton requestButton = new RideRequestButton(this);
//
//        RideParameters rideParams = new RideParameters.Builder()
//                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
//                .setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
//                .setDropoffLocation(37.795079, -122.4397805, "Embarcadero", "One Embarcadero Center, San Francisco")
//                .build();
//        requestButton.setRideParameters(rideParams);

    }

    public void initializeViews() {

        imageView = (ImageView) findViewById(R.id.detail_image);
        textViewTitle = (TextView) findViewById(R.id.detail_title);
        textViewDescription = (TextView) findViewById(R.id.detail_description);
        textViewWhen = (TextView) findViewById(R.id.detail_when);
        textViewAdd1 = (TextView) findViewById(R.id.detail_add1);
        textViewCategory = (TextView) findViewById(R.id.detail_category);

        fam = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        fab1 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item1);
        fab2 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item2_fb);
        fab3 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item3_messenger);

        locationButton = (Button) findViewById(R.id.detail_button_location);

    }

    public void initializeFacebookStuff() {

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        messageDialog = new MessageDialog(this);

    }

    public void setViews(Intent intent) {

        textViewTitle.setText(intent.getStringExtra(getString(R.string.key_event_title)));
        textViewDescription.setText(Html.fromHtml(intent.getStringExtra(getString(R.string.description_html))));
//        textViewDescription.setText(intent.getStringExtra(getString(R.string.description_html)));
        textViewDescription.setMovementMethod(LinkMovementMethod.getInstance());

        textViewWhen.setText(intent.getStringExtra(getString(R.string.key_event_time_date)));
        textViewAdd1.setText(intent.getStringExtra(getString(R.string.key_event_full_address)));
        textViewCategory.setText(intent.getStringExtra(getString(R.string.key_event_category)));

        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(findViewById(R.id.detail_image));

        if (intent.getStringExtra(getString(R.string.key_event_image)) != null) {
            Picasso.with(getApplicationContext()).load(intent.getStringExtra(getString(R.string.key_event_image))).into(imageView);

        } else {
            imageView.setImageResource(R.drawable.no_img);
        }

    }

}
