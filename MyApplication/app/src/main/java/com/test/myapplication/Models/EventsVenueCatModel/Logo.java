
package com.test.myapplication.Models.EventsVenueCatModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("aspect_ratio")
    @Expose
    private String aspectRatio;
    @SerializedName("edge_color")
    @Expose
    private String edgeColor;
    @SerializedName("edge_color_set")
    @Expose
    private Boolean edgeColorSet;

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
     * The aspectRatio
     */
    public String getAspectRatio() {
        return aspectRatio;
    }

    /**
     *
     * @param aspectRatio
     * The aspect_ratio
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     *
     * @return
     * The edgeColor
     */
    public String getEdgeColor() {
        return edgeColor;
    }

    /**
     *
     * @param edgeColor
     * The edge_color
     */
    public void setEdgeColor(String edgeColor) {
        this.edgeColor = edgeColor;
    }

    /**
     *
     * @return
     * The edgeColorSet
     */
    public Boolean getEdgeColorSet() {
        return edgeColorSet;
    }

    /**
     *
     * @param edgeColorSet
     * The edge_color_set
     */
    public void setEdgeColorSet(Boolean edgeColorSet) {
        this.edgeColorSet = edgeColorSet;
    }

}