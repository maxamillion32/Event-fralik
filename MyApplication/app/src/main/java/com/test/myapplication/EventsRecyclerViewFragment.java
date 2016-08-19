package com.test.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;

import java.util.ArrayList;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NehaRege on 8/16/16.
 */
public class EventsRecyclerViewFragment extends Fragment {

    private static final String API_KEY_EVENT_BRITE = "AMDMMKWPWFPOCAUYVIW2";
    public static final String CALL_ENQUE_ALL_EVENTS_KEY = "AllEvents";

    public static String TAG="Fragment";

    public static final String ARG_PAGE = "ARG_PAGE";
    private String title;
    private int page;

    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapterEvents rvAdapter;
//    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    public ArrayList<Event> allEventsdataList;
    
    private Bundle bundleAPIData;

    public static EventsRecyclerViewFragment newInstance(int page) {

        Log.i(TAG, "newInstance method *************************************************************");

        EventsRecyclerViewFragment eventsFragment = new EventsRecyclerViewFragment();



        Log.i(TAG, "newInstance of events fragment created");
        
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE,page);
        eventsFragment.setArguments(bundle);

        return eventsFragment;

    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allEventsdataList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: ");

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);

        rvLayoutManager = new GridLayoutManager(getActivity(),2);
//        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        Log.i(TAG, "onCreateView: calling loadfreeEvents() ");
        
        loadFreeEvents();

        Log.i(TAG, "onCreateView: completed");

        // TODO:       rvAdapter = new CustomAdapter(dataList, this);
        //TODO:        recyclerView.setAdapter(rvAdapter);
            
            rvAdapter = new CustomRecyclerViewAdapterEvents(getActivity(),allEventsdataList);

        recyclerView.setAdapter(rvAdapter);

        return rootView;

    }

    public void loadFreeEvents() {

        Log.i(TAG, "inside loadFreeEvents method ");

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

                    Log.i(TAG, "onResponse: start ----------------- ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: freeEventsObject "+response.body());


                    Log.i(TAG, "onResponse: initial data size = "+allEventsdataList.size());

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();

                    Log.i(TAG, "onResponse: ArrayList Data Size after adding = "+allEventsdataList.size());

                    Log.i(TAG, "onResponse: data = "+allEventsdataList);


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


}
