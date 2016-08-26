package com.test.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.google.android.gms.identity.intents.AddressConstants;
import com.squareup.picasso.Picasso;
import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.R;

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

    FloatingActionMenu fam;
    FloatingActionButton fab1, fab2, fab3;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    MessageDialog messageDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);

//        this.getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.FOCUS_FORWARD
//
//        );


        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE

        );

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);

        Log.i(TAG, "onCreateView: shareDialog instance created");

        messageDialog = new MessageDialog(this);


//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

//        mDecorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.fragment_detail);


        Log.i(TAG, "onCreate: detail activity created !!!!!!!");

        imageView = (ImageView) findViewById(R.id.detail_image);
        textViewTitle = (TextView) findViewById(R.id.detail_title);
        textViewDescription = (TextView) findViewById(R.id.detail_description);
        textViewWhen = (TextView) findViewById(R.id.detail_when);
        textViewAdd1 = (TextView) findViewById(R.id.detail_add1);
//        textViewAdd2 = (TextView) findViewById(R.id.detail_add2);
//        textViewCity = (TextView) findViewById(R.id.detail_city);
        textViewCategory = (TextView) findViewById(R.id.detail_category);


        fam = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        fab1 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item1);
        fab2 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item2_fb);
        fab3 = (FloatingActionButton) findViewById(R.id.floating_action_menu_item3_messenger);

        final Intent intent = getIntent();

        Log.i(TAG, "onCreate: Intent received");

//        javax.xml.bind.DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z")

        Log.i(TAG, "onCreate: Received Intent Title = "+ intent.getStringExtra(getString(R.string.key_event_title)));
        Log.i(TAG, "onCreate: Received Intent des = "+ intent.getStringExtra(getString(R.string.key_event_description)));

        textViewTitle.setText(intent.getStringExtra(getString(R.string.key_event_title)));
        textViewDescription.setText(intent.getStringExtra(getString(R.string.key_event_description)));
        textViewWhen.setText(intent.getStringExtra(getString(R.string.key_event_time_date)));
        textViewAdd1.setText(intent.getStringExtra(getString(R.string.key_event_full_address)));
//        textViewCity.setText(intent.getStringExtra(getString(R.string.key_event_city)));
        textViewCategory.setText(intent.getStringExtra(getString(R.string.key_event_category)));

        Picasso.with(getApplicationContext()).load(intent.getStringExtra(getString(R.string.key_event_image))).into(imageView);

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

//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentTitle(catArticalName)
//                    .setContentUrl(Uri.parse(catArticalUrl))
//                    .build();
//
//            shareDialog.show(linkContent);
//
//        }


//        selectedEvent = (Event)intent.("selectedEvent");


//        if(selectedEvent != null) {
//
//            if(selectedEvent.getLogo() != null) {
//                Picasso.with(getApplicationContext()).load(selectedEvent.getLogo().getUrl()).into(imageView);
//            }
//
//            textViewDescription.setText(selectedEvent.getDescription().getText());
//            textViewTitle.setText(selectedEvent.getName().getText());
//
//        }


    }
}
