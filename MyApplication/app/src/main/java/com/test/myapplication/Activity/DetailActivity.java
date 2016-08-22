package com.test.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;
import com.test.myapplication.R;

/**
 * Created by NehaRege on 8/22/16.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String TAG="DetailActivity";

    private Event selectedEvent;

    private String description;
    private String title;
    private String when;
    private String where;


    private ImageView imageView;

    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewWhen;
    private TextView textViewWhere;

    private Button buttonRegister;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_detail);

        Log.i(TAG, "onCreate: detail activity created !!!!!!!");

        imageView = (ImageView) findViewById(R.id.detail_image);
        textViewTitle = (TextView) findViewById(R.id.detail_title);
        textViewDescription = (TextView) findViewById(R.id.detail_description);
        textViewWhen = (TextView) findViewById(R.id.detail_when);
        textViewWhere = (TextView) findViewById(R.id.detail_where);
        buttonRegister = (Button) findViewById(R.id.detail_register_button);

        Intent intent = getIntent();

        Log.i(TAG, "onCreate: Intent received");


        description = intent.getStringExtra("description");
        title = intent.getStringExtra("title");
        when = intent.getStringExtra("when");

//        javax.xml.bind.DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z")

        Log.i(TAG, "onCreate: Received Intent Title = "+title);
        Log.i(TAG, "onCreate: Received Intent des = "+description);

        textViewTitle.setText(title);
        textViewDescription.setText(description);
        textViewWhere.setText(intent.getStringExtra("where"));
        textViewWhen.setText(intent.getStringExtra("when"));
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).into(imageView);


//        selectedEvent = (Event)intent.("selectedEvent");

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

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
