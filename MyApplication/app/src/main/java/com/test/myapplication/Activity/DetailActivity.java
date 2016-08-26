package com.test.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
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

    private FloatingActionMenu fam;
    private FloatingActionButton fab1, fab2, fab3;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    MessageDialog messageDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE

        );

        setContentView(R.layout.fragment_detail);

        initializeFacebookStuff();

        initializeViews();

        final Intent intent = getIntent();

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

    }

    public void initializeFacebookStuff() {

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        messageDialog = new MessageDialog(this);

    }

    public void setViews(Intent intent) {

        textViewTitle.setText(intent.getStringExtra(getString(R.string.key_event_title)));
        textViewDescription.setText(intent.getStringExtra(getString(R.string.key_event_description)));
        textViewDescription.setMovementMethod(LinkMovementMethod.getInstance());
        textViewWhen.setText(intent.getStringExtra(getString(R.string.key_event_time_date)));
        textViewAdd1.setText(intent.getStringExtra(getString(R.string.key_event_full_address)));
        textViewCategory.setText(intent.getStringExtra(getString(R.string.key_event_category)));

        YoYo.with(Techniques.RollIn)
                .duration(600)
                .playOn(findViewById(R.id.detail_image));

        Picasso.with(getApplicationContext()).load(intent.getStringExtra(getString(R.string.key_event_image))).into(imageView);

    }



}
