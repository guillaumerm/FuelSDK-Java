package com.exacttarget.fuelsdk;

import java.net.URL;
import java.lang.IllegalArgumentException;

import com.google.gson.annotations.Expose;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(
    path = "/platform/v1/ens-callbacks",
    primaryKey = "callbackId",
    collection = "",
    totalCount = ""
)
public class ENSCallback extends ETENSRestObject{
    @Expose
    @ExternalName("callbackId")
    private String callbackId = null;
    @Expose
    @ExternalName("callbackName")
    private String callbackName = null;
    @Expose
    @ExternalName("url")
    private String url = null;
    @Expose
    @ExternalName("maxBatchSize")
    private int maxBatchSize = 100;
    @Expose
    @ExternalName("status")
    private String status = null;
    @Expose
    @ExternalName("statusReason")
    private String statusReason = null;

    public final static int MIN_BATCH_SIZE = 100;
    public final static int MAX_BATCH_SIZE = 1000;

    /**
     * @return The Identifier of the ENSCallback object.
     */
    public String getCallbackId() {
        return callbackId;
    }

    /**
     * @param callbackId The Identifier of the ENSCallback object.
     */
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    /**
     * @return The name of the ENSCallback object.
     */
    public String getCallbackName() {
        return callbackName;
    }

    /**
     * @param callbackName The name of the ENSCallback object.
     */
    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    /**
     * @return The url of the ENSCallback object.
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url The url of the ENSCallback object.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return The max batch size of the ENSCallback object.
     */
    public int getMaxBatchSize() {
        return maxBatchSize;
    }

    /**
     * @param maxBatchSize The max batch size of the ENSCallback object.
     * @throws IllegalArgumentException
     */
    public void setMaxBatchSize(int maxBatchSize) {
        if (maxBatchSize < ENSCallback.MIN_BATCH_SIZE || maxBatchSize > ENSCallback.MAX_BATCH_SIZE) {
            throw new IllegalArgumentException("maxBatchSize must be between 100 and 1000");
        }
        this.maxBatchSize = maxBatchSize;
    }

    /**
     * 
     * @return The status of the ENSCallback object.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status of the ENSCallback object.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The status reason of the ENSCallback object.
     */
    public String getStatusReason() {
        return statusReason;
    }

    /**
     * @param statusReason The status reason of the ENSCallback object.
     */
    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    @Override
    public String getId() {
        return getCallbackId();
    }

    @Override
    public void setId(String id) {
        setCallbackId(id);
    }
}
