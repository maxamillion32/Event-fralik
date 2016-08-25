
package com.test.myapplication.Models.EventsVenueCatModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class End {

    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("utc")
    @Expose
    private String utc;

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The local
     */
    public String getLocal() {
        return local;
    }

    /**
     *
     * @param local
     * The local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     *
     * @return
     * The utc
     */
    public String getUtc() {
        return utc;
    }

    /**
     *
     * @param utc
     * The utc
     */
    public void setUtc(String utc) {
        this.utc = utc;
    }

}