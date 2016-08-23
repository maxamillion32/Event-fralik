
package com.test.myapplication.Models.CategoriesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("page_number")
    @Expose
    private Integer pageNumber;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;

    /**
     *
     * @return
     * The objectCount
     */
    public Integer getObjectCount() {
        return objectCount;
    }

    /**
     *
     * @param objectCount
     * The object_count
     */
    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

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

    /**
     *
     * @return
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The page_count
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}