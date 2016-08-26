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

    private static final String BASE_URL_EVENTS = "https://www.eventbriteapi.com/";

    private static final String API_KEY_EVENT_BRITE = "AMDMMKWPWFPOCAUYVIW2";
    public static String TAG="EventsRvFragment";
    public static final String KEY_ARG_PAGE = "KEY_ARG_PAGE";
    public static final String KEY_EVENT_TYPE = "KEY_EVENT_TYPE";


    public ArrayList<Event> eventsDataList;

    public String latitude, longitude;

    private int page;
    private String eventType;

    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapterEvents rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private Event selectedEvent;
    OnEventSelectedListener onEventSelectedListener;

    public static EventsRecyclerViewFragment newInstance(int page, String eventType) {

        EventsRecyclerViewFragment eventsFragment = new EventsRecyclerViewFragment();

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

        eventsDataList = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events_recycler,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_event_recycler_view);

        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        latitude = sharedPreferences.getString(getString(R.string.key_location_services_latitude),null);
        longitude = sharedPreferences.getString(getString(R.string.key_location_services_longitude),null);

        Log.i(TAG, "onCreateView: lat long is = "+latitude+" , "+longitude);

        if( eventType.equals(getString(R.string.event_type_all_events))) {

            loadAllNearbyEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_music))) {

            loadMusicEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_food_drink))) {

            loadFoodAndDrinkEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_outdoor))) {

            loadOutdoorEvents(latitude,longitude);

        }  else if (eventType.equals(getString(R.string.event_type_entertainment))) {

            loadEntertainmentEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_hobbies))) {

            loadHobbiesEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_air_boat))) {

            loadAirBoatEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_arts))) {

            loadArtsEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_sports))) {

            loadSportsFitnessEvents(latitude,longitude);

        } else if (eventType.equals(getString(R.string.event_type_health))) {

            loadHealthEvents(latitude,longitude);

        }

        rvAdapter = new CustomRecyclerViewAdapterEvents(getActivity(), eventsDataList,this);

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
        }

    }

    @Override
    public void onItemClick(int position) {

        if(eventsDataList != null) {

            selectedEvent = eventsDataList.get(position);

            onEventSelectedListener.onEventSelected(selectedEvent);

        }

    }

    public void loadAllNearbyEvents(String latitude, String longitude) {

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callAllNearbyEvents = request.getAllNearbyEvents("20mi",latitude,longitude,"Bearer "+ API_KEY_EVENT_BRITE);

        callAllNearbyEvents.enqueue(new Callback<FreeEventsObject>() {

            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

    public void loadMusicEvents(String latitude, String longitude) {
        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("103","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {
                    FreeEventsObject freeEventsObject = response.body();
                    eventsDataList.addAll(freeEventsObject.getEvents());
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
                .baseUrl(BASE_URL_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callMusic = request.getCategoryEvents("105","20mi",latitude,longitude,"Bearer "+API_KEY_EVENT_BRITE);

        callMusic.enqueue(new Callback<FreeEventsObject>() {
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

                    eventsDataList.addAll(freeEventsObject.getEvents());

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
                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

    public void loadEntertainmentEvents(String latitude, String longitude) {

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

                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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
                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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

        String BASE_URL_FREE_EVENTS = "https://www.eventbriteapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FREE_EVENTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EventBriteAPIService request = retrofit.create(EventBriteAPIService.class);

        Call<FreeEventsObject> callAllFreeEvents = request.getAllFreeEvents("free","Bearer "+ API_KEY_EVENT_BRITE);
        Call<FreeEventsObject> callAllEvents = request.getAllEvents("Bearer "+ API_KEY_EVENT_BRITE);
        Call<FreeEventsObject> callAllFreePopular = request.getAllFreePopularEvents("free","popular",
                "America","Bearer "+ API_KEY_EVENT_BRITE);

        callAllFreeEvents.enqueue(new Callback<FreeEventsObject>() {
            
            @Override
            public void onResponse(Call<FreeEventsObject> call, Response<FreeEventsObject> response) {
                try {

                    FreeEventsObject freeEventsObject = response.body();

                    eventsDataList.addAll(freeEventsObject.getEvents());

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
    //endregion

}
