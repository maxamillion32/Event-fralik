
package com.test.myapplication.Models.EventsVenueCatModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.myapplication.Models.FreeEventsModel.Location;

public class EventsVenueCatObject {

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("events")
    @Expose
    private List<Event> events = new ArrayList<Event>();
    @SerializedName("location")
    @Expose
    private Location location;

    /**
     *
     * @return
     * The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     *
     * @param pagination
     * The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     *
     * @return
     * The events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @param events
     * The events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}