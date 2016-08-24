package com.test.myapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.myapplication.MainActivity;
import com.test.myapplication.OnEventSelectedListener;
import com.test.myapplication.RvAdapter.CustomRecyclerViewAdapterEvents;
import com.test.myapplication.APIService.EventBriteAPIService;
import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;
import com.test.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NehaRege on 8/16/16.
 */
public class EventsRecyclerViewFragment extends Fragment
        implements CustomRecyclerViewAdapterEvents.OnRecyclerViewItemClickListener {

    private static final String API_KEY_EVENT_BRITE = "AMDMMKWPWFPOCAUYVIW2";
    public static final String CALL_ENQUE_ALL_EVENTS_KEY = "AllEvents";
    public static final String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

    public static String TAG="EventsFragment";

    public static final String ARG_PAGE = "ARG_PAGE";
    private String title;
    private int page;

    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapterEvents rvAdapter;

    private Event selectedEvent;
    OnEventSelectedListener onEventSelectedListener;

    private RecyclerView.LayoutManager rvLayoutManager;

    public ArrayList<Event> allEventsdataList;

    public String latitude, longitude;
    
    private Bundle bundleAPIData;

    public static EventsRecyclerViewFragment newInstance(int page) {

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        latitude = sharedPreferences.getString("latitude",null);
        longitude = sharedPreferences.getString("longitude",null);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: ");

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);

//        rvLayoutManager = new GridLayoutManager(getActivity(),2);
//        rvLayoutManager = new StaggeredGridLayoutManager()
        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        Log.i(TAG, "onCreateView: calling loadfreeEvents() ");



        Log.i(TAG, "onCreateView: lat long is = "+latitude+" , "+longitude);

        if(latitude!=null && longitude!=null) {

            Log.i(TAG, "onCreateView: lat long not null");
            Log.i(TAG, "onCreateView: calling neabyevents method");

            loadAllNearbyEvents(latitude,longitude);

        } else {

            loadFreeEvents();

        }

        Log.i(TAG, "onCreateView: completed");
            
        rvAdapter = new CustomRecyclerViewAdapterEvents(getActivity(),allEventsdataList,this);

        recyclerView.setAdapter(rvAdapter);

        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onEventSelectedListener = (OnEventSelectedListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
//            throw new ClassCastException(getActivity().toString() + " must implement OnArticleSelectedListener");
        }


    }

    @Override
    public void onItemClick(int position) {

        Log.i(TAG, "onItemClick: position is "+position);

        if(allEventsdataList != null) {

            Log.i(TAG, "onItemClick: data list NOT null");
            
            selectedEvent = allEventsdataList.get(position);

            Log.i(TAG, "onItemClick: selected event name is = "+selectedEvent.getName().getText());
            onEventSelectedListener.onEventSelected(selectedEvent);

        }

    }

    public void loadAllNearbyEvents(String latitude, String longitude) {

        Log.i(TAG, "loadAllNearbyEvents: entered");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callAllNearbyEvents = request.getAllNearbyEvents("20mi",latitude,longitude,"Bearer "+ API_KEY_EVENT_BRITE);

        callAllNearbyEvents.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    Log.i(TAG, "onResponse: start ----------------- ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: freeEventsObject "+response.body());


                    Log.i(TAG, "onResponse: initial data size = "+allEventsdataList.size());

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    FreeEventsObject obj = new FreeEventsObject();

                    Log.i(TAG, "onResponse: page size = "+response.body().getPagination().getPageSize());
                    Log.i(TAG, "onResponse: page size = "+response.body().getPagination().getPageNumber());

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


    //region EventBrite API Free Events
    public void loadFreeEvents() {

        Log.i(TAG, "inside loadFreeEvents method ");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "loadFreeEvents: retrofit object created");

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callAllFreeEvents = request.getAllFreeEvents("free","Bearer "+ API_KEY_EVENT_BRITE);
        Call<FreeEventsObject> callAllEvents = request.getAllEvents("Bearer "+ API_KEY_EVENT_BRITE);
        Call<FreeEventsObject> callAllFreePopular = request.getAllFreePopularEvents("free","popular",
                "America","Bearer "+ API_KEY_EVENT_BRITE);
        Call<FreeEventsObject> callAllNearbyEvents = request.getAllNearbyEvents("20mi","lat","longi","Bearer "+ API_KEY_EVENT_BRITE);

        callAllFreeEvents.enqueue(new Callback<FreeEventsObject>() {
            
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    Log.i(TAG, "onResponse: start ----------------- ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: freeEventsObject "+response.body());


                    Log.i(TAG, "onResponse: initial data size = "+allEventsdataList.size());

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    FreeEventsObject obj = new FreeEventsObject();

                    Log.i(TAG, "onResponse: page size = "+response.body().getPagination().getPageSize());
                    Log.i(TAG, "onResponse: page size = "+response.body().getPagination().getPageNumber());

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
    //endregion




}
