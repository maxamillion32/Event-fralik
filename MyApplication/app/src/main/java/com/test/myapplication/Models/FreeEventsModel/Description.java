package com.test.myapplication.Models.FreeEventsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NehaRege on 8/18/16.
 */
public class Description {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("html")
    @Expose
    private String html;

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The html
     */
    public String getHtml() {
        return html;
    }

    /**
     *
     * @param html
     * The html
     */
    public void setHtml(String html) {
        this.html = html;
    }




}
