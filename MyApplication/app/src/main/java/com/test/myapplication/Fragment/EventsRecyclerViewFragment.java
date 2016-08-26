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
import android.widget.Button;

import com.test.myapplication.OnEventSelectedListener;
import com.test.myapplication.RvAdapter.CustomRecyclerViewAdapterEvents;
import com.test.myapplication.APIService.EventBriteAPIService;
import com.test.myapplication.Models.FreeEventsModel.Event;
import com.test.myapplication.Models.FreeEventsModel.FreeEventsObject;
import com.test.myapplication.R;

import java.util.ArrayList;

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

    public static String TAG="EventsRvFragment";

    public static final String KEY_ARG_PAGE = "KEY_ARG_PAGE";
    public static final String KEY_EVENT_TYPE = "KEY_EVENT_TYPE";
    private String title;
    private int page;
    private String eventType;

    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapterEvents rvAdapter;

    private Event selectedEvent;
    OnEventSelectedListener onEventSelectedListener;

    private RecyclerView.LayoutManager rvLayoutManager;

    public ArrayList<Event> allEventsdataList;

    public String latitude, longitude;

//    private Button buttonNearMe;
//    private Button buttonAllEvents;



    public static EventsRecyclerViewFragment newInstance(int page, String eventType) {

        EventsRecyclerViewFragment eventsFragment = new EventsRecyclerViewFragment();

        Log.i(TAG, "newInstance of events fragment created");
        
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ARG_PAGE,page);
        bundle.putString(KEY_EVENT_TYPE,eventType);
        eventsFragment.setArguments(bundle);

        return eventsFragment;

    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate: ");

        page = getArguments().getInt(KEY_ARG_PAGE);
        eventType = getArguments().getString(KEY_EVENT_TYPE);

        allEventsdataList = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: ");

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);

//        rvLayoutManager = new GridLayoutManager(getActivity(),2);

        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

//        buttonNearMe = (Button) rootView.findViewById(R.id.fragment_events_button_nearby_events);
//        buttonAllEvents = (Button) rootView.findViewById(R.id.fragment_events_button_all_events);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        latitude = sharedPreferences.getString(getString(R.string.key_location_services_latitude),null);
        longitude = sharedPreferences.getString(getString(R.string.key_location_services_longitude),null);

        Log.i(TAG, "onCreateView: lat long is = "+latitude+" , "+longitude);

//        loadFreeEvents();

//        buttonNearMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(latitude!=null && longitude!=null) {
//
//                    Log.i(TAG, "onCreateView: lat long not null");
//                    Log.i(TAG, "onCreateView: calling neabyevents method");
//
//                    loadAllNearbyEvents(latitude,longitude);
//                    rvAdapter.notifyDataSetChanged();
//
//                } else {
//                    Toast.makeText(getContext(),"Unable to find the location. Please check your location services",
//                            Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

//        buttonAllEvents.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFreeEvents();
//                rvAdapter.notifyDataSetChanged();
//            }
//        });

//        if(latitude!=null && longitude!=null) {

//            Log.i(TAG, "onCreateView: lat long not null");
//            Log.i(TAG, "onCreateView: calling neabyevents method");

        if( eventType.equals(getString(R.string.event_type_all_events))) {

            Log.i(TAG, "onCreateView: EVENT");

            Log.i(TAG, "onCreateView: event type is = "+eventType);

            loadAllNearbyEvents(latitude,longitude);
//            loadMusicEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_music))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadMusicEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_food_drink))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadFoodAndDrinkEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_outdoor))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadOutdoorEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_entertainment))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadEntertainmentEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_hobbies))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadHobbiesEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_air_boat))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadAirBoatEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_arts))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadArtsEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_sports))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadSportsFitnessEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_health))) {
            Log.i(TAG, "onCreateView: MUSIC ");

            Log.i(TAG, "onCreateView: event type is "+eventType);

            loadHealthEvents(latitude,longitude);

        }



//        } else {
//
//            loadFreeEvents();
//
//        }

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
        
        



//        YoYo.with(Techniques.Tada)
//                .duration(700)
//                .playOn(findViewById(R.id.edit_area));


        Log.i(TAG, "onItemClick: position is "+position);

        if(allEventsdataList != null) {

            Log.i(TAG, "onItemClick: data list NOT null");
            
            selectedEvent = allEventsdataList.get(position);

            Log.i(TAG, "onItemClick: eventType = "+eventType);
            

            Log.i(TAG, "onItemClick: selected event name is = "+selectedEvent.getName().getText());

            Log.i(TAG, "onItemClick: position = "+ position);

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

    public void loadMusicEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("103","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {
            }
        });

    }

    public void loadArtsEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("105","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {
            }
        });

    }

    public void loadHobbiesEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("119","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {
            }
        });

    }

    public void loadAirBoatEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("118","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {
            }
        });

    }

    public void loadHealthEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("107","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    rvAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FreeEventsObject> call, Throwable t) {
            }
        });

    }

    public void loadFoodAndDrinkEvents(String latitude, String longitude) {

        Log.i(TAG, "loadMusicEvents: ");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("110","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    Log.i(TAG, "onResponse: ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: response = "+freeEventsObject.getPagination() );

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    Log.i(TAG, "onResponse: data = "+allEventsdataList );
                    Log.i(TAG, "onResponse: data size = "+allEventsdataList.size() );

                    Log.i(TAG, "onResponse: cat ID = "+allEventsdataList.get(2).getCategoryId());

                    Log.i(TAG, "onResponse: cat = "+allEventsdataList.get(2).getCategory().getName());

                    rvAdapter.notifyDataSetChanged();
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

    public void loadEntertainmentEvents(String latitude, String longitude) {

        Log.i(TAG, "loadMusicEvents: ");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("104","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    Log.i(TAG, "onResponse: ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: response = "+freeEventsObject.getPagination() );

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    Log.i(TAG, "onResponse: data = "+allEventsdataList );
                    Log.i(TAG, "onResponse: data size = "+allEventsdataList.size() );

                    Log.i(TAG, "onResponse: cat ID = "+allEventsdataList.get(2).getCategoryId());

                    Log.i(TAG, "onResponse: cat = "+allEventsdataList.get(2).getCategory().getName());

                    rvAdapter.notifyDataSetChanged();
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

    public void loadOutdoorEvents(String latitude, String longitude) {

        Log.i(TAG, "loadMusicEvents: ");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("109","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    Log.i(TAG, "onResponse: ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: response = "+freeEventsObject.getPagination() );

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    Log.i(TAG, "onResponse: data = "+allEventsdataList );
                    Log.i(TAG, "onResponse: data size = "+allEventsdataList.size() );

                    Log.i(TAG, "onResponse: cat ID = "+allEventsdataList.get(2).getCategoryId());

                    Log.i(TAG, "onResponse: cat = "+allEventsdataList.get(2).getCategory().getName());

                    rvAdapter.notifyDataSetChanged();
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

    public void loadSportsFitnessEvents(String latitude, String longitude) {

        Log.i(TAG, "loadMusicEvents: ");

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("108","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    Log.i(TAG, "onResponse: ");

                    FreeEventsObject freeEventsObject = response.body();

                    Log.i(TAG, "onResponse: response = "+freeEventsObject.getPagination() );

                    allEventsdataList.addAll(freeEventsObject.getEvents());

                    Log.i(TAG, "onResponse: data = "+allEventsdataList );
                    Log.i(TAG, "onResponse: data size = "+allEventsdataList.size() );

                    Log.i(TAG, "onResponse: cat ID = "+allEventsdataList.get(2).getCategoryId());

                    Log.i(TAG, "onResponse: cat = "+allEventsdataList.get(2).getCategory().getName());

                    rvAdapter.notifyDataSetChanged();
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
