
package com.test.myapplication.Models.FreeEventsModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketClass {

    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("donation")
    @Expose
    private Boolean donation;
    @SerializedName("free")
    @Expose
    private Boolean free;
    @SerializedName("minimum_quantity")
    @Expose
    private Integer minimumQuantity;
    @SerializedName("maximum_quantity")
    @Expose
    private Integer maximumQuantity;
    @SerializedName("maximum_quantity_per_order")
    @Expose
    private Integer maximumQuantityPerOrder;
    @SerializedName("on_sale_status")
    @Expose
    private String onSaleStatus;
    @SerializedName("sales_start")
    @Expose
    private String salesStart;
    @SerializedName("sales_end")
    @Expose
    private String salesEnd;
    @SerializedName("hide_description")
    @Expose
    private Boolean hideDescription;
    @SerializedName("variants")
    @Expose
    private List<Object> variants = new ArrayList<Object>();
    @SerializedName("has_pdf_ticket")
    @Expose
    private Object hasPdfTicket;
    @SerializedName("sales_channels")
    @Expose
    private Object salesChannels;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("id")
    @Expose
    private String id;

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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The donation
     */
    public Boolean getDonation() {
        return donation;
    }

    /**
     *
     * @param donation
     * The donation
     */
    public void setDonation(Boolean donation) {
        this.donation = donation;
    }

    /**
     *
     * @return
     * The free
     */
    public Boolean getFree() {
        return free;
    }

    /**
     *
     * @param free
     * The free
     */
    public void setFree(Boolean free) {
        this.free = free;
    }

    /**
     *
     * @return
     * The minimumQuantity
     */
    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    /**
     *
     * @param minimumQuantity
     * The minimum_quantity
     */
    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    /**
     *
     * @return
     * The maximumQuantity
     */
    public Integer getMaximumQuantity() {
        return maximumQuantity;
    }

    /**
     *
     * @param maximumQuantity
     * The maximum_quantity
     */
    public void setMaximumQuantity(Integer maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    /**
     *
     * @return
     * The maximumQuantityPerOrder
     */
    public Integer getMaximumQuantityPerOrder() {
        return maximumQuantityPerOrder;
    }

    /**
     *
     * @param maximumQuantityPerOrder
     * The maximum_quantity_per_order
     */
    public void setMaximumQuantityPerOrder(Integer maximumQuantityPerOrder) {
        this.maximumQuantityPerOrder = maximumQuantityPerOrder;
    }

    /**
     *
     * @return
     * The onSaleStatus
     */
    public String getOnSaleStatus() {
        return onSaleStatus;
    }

    /**
     *
     * @param onSaleStatus
     * The on_sale_status
     */
    public void setOnSaleStatus(String onSaleStatus) {
        this.onSaleStatus = onSaleStatus;
    }

    /**
     *
     * @return
     * The salesStart
     */
    public String getSalesStart() {
        return salesStart;
    }

    /**
     *
     * @param salesStart
     * The sales_start
     */
    public void setSalesStart(String salesStart) {
        this.salesStart = salesStart;
    }

    /**
     *
     * @return
     * The salesEnd
     */
    public String getSalesEnd() {
        return salesEnd;
    }

    /**
     *
     * @param salesEnd
     * The sales_end
     */
    public void setSalesEnd(String salesEnd) {
        this.salesEnd = salesEnd;
    }

    /**
     *
     * @return
     * The hideDescription
     */
    public Boolean getHideDescription() {
        return hideDescription;
    }

    /**
     *
     * @param hideDescription
     * The hide_description
     */
    public void setHideDescription(Boolean hideDescription) {
        this.hideDescription = hideDescription;
    }

    /**
     *
     * @return
     * The variants
     */
    public List<Object> getVariants() {
        return variants;
    }

    /**
     *
     * @param variants
     * The variants
     */
    public void setVariants(List<Object> variants) {
        this.variants = variants;
    }

    /**
     *
     * @return
     * The hasPdfTicket
     */
    public Object getHasPdfTicket() {
        return hasPdfTicket;
    }

    /**
     *
     * @param hasPdfTicket
     * The has_pdf_ticket
     */
    public void setHasPdfTicket(Object hasPdfTicket) {
        this.hasPdfTicket = hasPdfTicket;
    }

    /**
     *
     * @return
     * The salesChannels
     */
    public Object getSalesChannels() {
        return salesChannels;
    }

    /**
     *
     * @param salesChannels
     * The sales_channels
     */
    public void setSalesChannels(Object salesChannels) {
        this.salesChannels = salesChannels;
    }

    /**
     *
     * @return
     * The eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     *
     * @param eventId
     * The event_id
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
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

}