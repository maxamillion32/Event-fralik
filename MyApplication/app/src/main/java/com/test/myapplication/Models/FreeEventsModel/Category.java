
package com.test.myapplication.Models.FreeEventsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_localized")
    @Expose
    private String nameLocalized;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("short_name_localized")
    @Expose
    private String shortNameLocalized;

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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The nameLocalized
     */
    public String getNameLocalized() {
        return nameLocalized;
    }

    /**
     *
     * @param nameLocalized
     * The name_localized
     */
    public void setNameLocalized(String nameLocalized) {
        this.nameLocalized = nameLocalized;
    }

    /**
     *
     * @return
     * The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     * The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     * The shortNameLocalized
     */
    public String getShortNameLocalized() {
        return shortNameLocalized;
    }

    /**
     *
     * @param shortNameLocalized
     * The short_name_localized
     */
    public void setShortNameLocalized(String shortNameLocalized) {
        this.shortNameLocalized = shortNameLocalized;
    }

}