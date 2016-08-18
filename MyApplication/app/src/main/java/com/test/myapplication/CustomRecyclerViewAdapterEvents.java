package com.test.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NehaRege on 8/16/16.
 */
public class CustomRecyclerViewAdapterEvents extends RecyclerView.Adapter<CustomRecyclerViewAdapterEvents.ViewHolder> {

    //TODO: define arraylist for the data
    private ArrayList<String> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDate;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.card_layout_event_image);
            textViewTitle = (TextView) itemView.findViewById(R.id.card_layout_event_title);
            textViewDate = (TextView) itemView.findViewById(R.id.card_layout_event_date);
            button = (Button) itemView.findViewById(R.id.card_layout_event_button_share);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: handle the click here for share feature using share intents
                }
            });

        }
    }

    public CustomRecyclerViewAdapterEvents(ArrayList<String> inComingData) {

        if(inComingData != null){
            this.data = inComingData;
        } else {
            this.data = new ArrayList<>();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View cardLayout = LayoutInflater.from(context).inflate(R.layout.rv_card_layout_events,parent,false);

        ViewHolder viewHolder = new ViewHolder(cardLayout);

        return viewHolder;
    }

    //// TODO: 8/17/16
//    String dataItem = data.get(position);
//
//    /**
//     * Pull out the inflated TextView/ImageView references out of our SampleViewHolder
//     * instance.
//     *
//     * Look at the constructor of SampleViewHolder() and note that variable fields
//     * 'imageView' and 'textView' are both public ( which is why we don't need a
//     * getter ).
//     */
//    TextView textView = holder.textView;
//    ImageView imageView = holder.imageView;
//
//    // put our dataItem string as text into the text view
//    textView.setText(dataItem);
//
//    // set the launcher icon as our image resource
//    imageView.setImageResource(R.mipmap.ic_launcher);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView textView = holder.textViewTitle;
        TextView textView1 = holder.textViewDate;

        ImageView imageView = holder.imageView;
        Button button = holder.button;

        imageView.setImageResource(R.drawable.dog);
        textView.setText("EVENT TITLE");
        textView1.setText("AUG 26, 2016");

        //TODO: get the data from the arrayList and set the views (textView, imageView) with the data received



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
