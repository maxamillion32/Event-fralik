package com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by NehaRege on 8/16/16.
 */
public class EventsFragmentRecycler extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private LinkedList<String> dataList = new LinkedList<String>();

    public static EventsFragmentRecycler newInstance(int page,String title) {

        EventsFragmentRecycler eventsFragment = new EventsFragmentRecycler();

        Bundle bundle = new Bundle();
        bundle.putInt("page0",page);
        bundle.putString("Events",title);

        eventsFragment.setArguments(bundle);

        return eventsFragment;



    }

//    public static FirstFragment newInstance(int page, String title) {
//        FirstFragment fragmentFirst = new FirstFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        fragmentFirst.setArguments(args);
//        return fragmentFirst;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_first, container, false);
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);
//        return view;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        // TODO:       rvAdapter = new CustomAdapter(dataList, this);
        //TODO:          recyclerView.setAdapter(rvAdapter);


//        rvAdapter = new CustomRecyclerViewAdapterEvents(dataList,this);

        return rootView;

    }







}
