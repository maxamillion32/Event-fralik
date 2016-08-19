package com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.myapplication.Models.FreeEventsModel.Event;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by NehaRege on 8/16/16.
 */
public class EventsRecyclerViewFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private String title;
    private int page;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private ArrayList<Event> allEventsdataList;

    public static EventsRecyclerViewFragment newInstance(int page) {

        EventsRecyclerViewFragment eventsFragment = new EventsRecyclerViewFragment();



        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE,page);
        eventsFragment.setArguments(bundle);

        return eventsFragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        // TODO:       rvAdapter = new CustomAdapter(dataList, this);
        //TODO:        recyclerView.setAdapter(rvAdapter);

        Bundle bundle = getArguments();

        if(bundle != null) {
            if(bundle.containsKey(MainActivity.CALL_ENQUE_ALL_EVENTS_KEY)) {
                allEventsdataList = (ArrayList<Event>) getArguments().getSerializable(MainActivity.CALL_ENQUE_ALL_EVENTS_KEY);
                rvAdapter = new CustomRecyclerViewAdapterEvents(getActivity(),allEventsdataList);


            }
        }

        ArrayList<String> s = new ArrayList<>();
        s.add("hello");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
        s.add("a");
//        rvAdapter = new CustomRecyclerViewAdapterEvents(s);
        recyclerView.setAdapter(rvAdapter);


//        rvAdapter = new CustomRecyclerViewAdapterEvents(dataList,this);

        return rootView;

    }







}
