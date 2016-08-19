
package com.test.myapplication.Models.FreeEventsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("page_number")
    @Expose
    private Integer pageNumber;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;

    /**
     *
     * @return
     * The pageNumber
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     *
     * @param pageNumber
     * The page_number
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     *
     * @return
     * The pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     *
     * @param pageSize
     * The page_size
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}