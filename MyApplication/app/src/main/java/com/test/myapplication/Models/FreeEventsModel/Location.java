
package com.test.myapplication.Models.FreeEventsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("within")
    @Expose
    private String within;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The within
     */
    public String getWithin() {
        return within;
    }

    /**
     *
     * @param within
     * The within
     */
    public void setWithin(String within) {
        this.within = within;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}