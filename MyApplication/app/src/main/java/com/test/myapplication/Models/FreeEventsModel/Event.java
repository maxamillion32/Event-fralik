package com.test.myapplication.Models.FreeEventsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("capacity_is_custom")
    @Expose
    private Boolean capacityIsCustom;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("listed")
    @Expose
    private Boolean listed;
    @SerializedName("shareable")
    @Expose
    private Boolean shareable;
    @SerializedName("online_event")
    @Expose
    private Boolean onlineEvent;
    @SerializedName("tx_time_limit")
    @Expose
    private Integer txTimeLimit;
    @SerializedName("hide_start_date")
    @Expose
    private Boolean hideStartDate;
    @SerializedName("hide_end_date")
    @Expose
    private Boolean hideEndDate;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("is_locked")
    @Expose
    private Boolean isLocked;
    @SerializedName("privacy_setting")
    @Expose
    private String privacySetting;
    @SerializedName("is_series")
    @Expose
    private Boolean isSeries;
    @SerializedName("is_series_parent")
    @Expose
    private Boolean isSeriesParent;
    @SerializedName("is_reserved_seating")
    @Expose
    private Boolean isReservedSeating;
    @SerializedName("logo_id")
    @Expose
    private String logoId;
    @SerializedName("organizer_id")
    @Expose
    private String organizerId;
    @SerializedName("venue_id")
    @Expose
    private String venueId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("format_id")
    @Expose
    private String formatId;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("logo")
    @Expose
    private Logo logo;

    /**
     *
     * @return
     * The name
     */
    public Name getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The start
     */
    public Start getStart() {
        return start;
    }

    /**
     *
     * @param start
     * The start
     */
    public void setStart(Start start) {
        this.start = start;
    }

    /**
     *
     * @return
     * The end
     */
    public End getEnd() {
        return end;
    }

    /**
     *
     * @param end
     * The end
     */
    public void setEnd(End end) {
        this.end = end;
    }

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The changed
     */
    public String getChanged() {
        return changed;
    }

    /**
     *
     * @param changed
     * The changed
     */
    public void setChanged(String changed) {
        this.changed = changed;
    }

    /**
     *
     * @return
     * The capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     *
     * @param capacity
     * The capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return
     * The capacityIsCustom
     */
    public Boolean getCapacityIsCustom() {
        return capacityIsCustom;
    }

    /**
     *
     * @param capacityIsCustom
     * The capacity_is_custom
     */
    public void setCapacityIsCustom(Boolean capacityIsCustom) {
        this.capacityIsCustom = capacityIsCustom;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The listed
     */
    public Boolean getListed() {
        return listed;
    }

    /**
     *
     * @param listed
     * The listed
     */
    public void setListed(Boolean listed) {
        this.listed = listed;
    }

    /**
     *
     * @return
     * The shareable
     */
    public Boolean getShareable() {
        return shareable;
    }

    /**
     *
     * @param shareable
     * The shareable
     */
    public void setShareable(Boolean shareable) {
        this.shareable = shareable;
    }

    /**
     *
     * @return
     * The onlineEvent
     */
    public Boolean getOnlineEvent() {
        return onlineEvent;
    }

    /**
     *
     * @param onlineEvent
     * The online_event
     */
    public void setOnlineEvent(Boolean onlineEvent) {
        this.onlineEvent = onlineEvent;
    }

    /**
     *
     * @return
     * The txTimeLimit
     */
    public Integer getTxTimeLimit() {
        return txTimeLimit;
    }

    /**
     *
     * @param txTimeLimit
     * The tx_time_limit
     */
    public void setTxTimeLimit(Integer txTimeLimit) {
        this.txTimeLimit = txTimeLimit;
    }

    /**
     *
     * @return
     * The hideStartDate
     */
    public Boolean getHideStartDate() {
        return hideStartDate;
    }

    /**
     *
     * @param hideStartDate
     * The hide_start_date
     */
    public void setHideStartDate(Boolean hideStartDate) {
        this.hideStartDate = hideStartDate;
    }

    /**
     *
     * @return
     * The hideEndDate
     */
    public Boolean getHideEndDate() {
        return hideEndDate;
    }

    /**
     *
     * @param hideEndDate
     * The hide_end_date
     */
    public void setHideEndDate(Boolean hideEndDate) {
        this.hideEndDate = hideEndDate;
    }

    /**
     *
     * @return
     * The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     *
     * @param locale
     * The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     *
     * @return
     * The isLocked
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     *
     * @param isLocked
     * The is_locked
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     *
     * @return
     * The privacySetting
     */
    public String getPrivacySetting() {
        return privacySetting;
    }

    /**
     *
     * @param privacySetting
     * The privacy_setting
     */
    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }

    /**
     *
     * @return
     * The isSeries
     */
    public Boolean getIsSeries() {
        return isSeries;
    }

    /**
     *
     * @param isSeries
     * The is_series
     */
    public void setIsSeries(Boolean isSeries) {
        this.isSeries = isSeries;
    }

    /**
     *
     * @return
     * The isSeriesParent
     */
    public Boolean getIsSeriesParent() {
        return isSeriesParent;
    }

    /**
     *
     * @param isSeriesParent
     * The is_series_parent
     */
    public void setIsSeriesParent(Boolean isSeriesParent) {
        this.isSeriesParent = isSeriesParent;
    }

    /**
     *
     * @return
     * The isReservedSeating
     */
    public Boolean getIsReservedSeating() {
        return isReservedSeating;
    }

    /**
     *
     * @param isReservedSeating
     * The is_reserved_seating
     */
    public void setIsReservedSeating(Boolean isReservedSeating) {
        this.isReservedSeating = isReservedSeating;
    }

    /**
     *
     * @return
     * The logoId
     */
    public String getLogoId() {
        return logoId;
    }

    /**
     *
     * @param logoId
     * The logo_id
     */
    public void setLogoId(String logoId) {
        this.logoId = logoId;
    }

    /**
     *
     * @return
     * The organizerId
     */
    public String getOrganizerId() {
        return organizerId;
    }

    /**
     *
     * @param organizerId
     * The organizer_id
     */
    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    /**
     *
     * @return
     * The venueId
     */
    public String getVenueId() {
        return venueId;
    }

    /**
     *
     * @param venueId
     * The venue_id
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    /**
     *
     * @return
     * The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @param categoryId
     * The category_id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return
     * The subcategoryId
     */
    public String getSubcategoryId() {
        return subcategoryId;
    }

    /**
     *
     * @param subcategoryId
     * The subcategory_id
     */
    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    /**
     *
     * @return
     * The formatId
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     *
     * @param formatId
     * The format_id
     */
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    /**
     *
     * @return
     * The resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     *
     * @param resourceUri
     * The resource_uri
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    /**
     *
     * @return
     * The logo
     */
    public Logo getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(Logo logo) {
        this.logo = logo;
    }

}