package com.test.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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

    


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        Intent intent = getIntent();

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
