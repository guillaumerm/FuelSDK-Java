package com.exacttarget.fuelsdk;

import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.google.gson.annotations.Expose;

@RestObject(path = "/platform/v1/ens-subscriptions-by-cb", primaryKey = "callbackId", collection = "", totalCount = "")
public class ENSSubscriptionByCallback extends ETENSRestObject {
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
    @ExternalName("subscriptionId")
    private String subscriptionId = null;
    @Expose
    @ExternalName("subscriptionName")
    private String subscriptionName = null;
    @Expose
    @ExternalName("eventCategoryTypes")
    private List<String> eventCategoryTypes = new ArrayList<String>();
    @Expose
    @ExternalName("filters")
    private List<String> filters = new ArrayList<String>();
    @Expose
    @ExternalName("status")
    private String status = null;
    @Expose
    @ExternalName("statusReason")
    private String statusReason = null;

    @Override
    public String getId() {
        return this.callbackId;
    }

    @Override
    public void setId(String id) {
        this.callbackId = id;
    }

    /**
     * 
     * @return The callback ID of this ENSSubscriptionByCallback object.
     */
    public String getCallbackId() {
        return callbackId;
    }

    /**
     * 
     * @param callbackId The callback ID of this ENSSubscriptionByCallback object.
     */
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    /**
     * 
     * @return The callback name of this ENSSubscriptionByCallback object.
     */
    public String getCallbackName() {
        return callbackName;
    }

    /**
     * 
     * @return The url of this ENSSubscriptionByCallback object.
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url The url of this ENSSubscriptionByCallback object.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return The max batch size of this ENSSubscriptionByCallback object.
     */
    public int getMaxBatchSize() {
        return maxBatchSize;
    }

    /**
     * 
     * @param maxBatchSize The max batch size of this ENSSubscriptionByCallback object.
     */
    public void setMaxBatchSize(int maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }

    /**
     * 
     * @return The subscriptionID of this ENSSubscriptionByCallback object.
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * 
     * @param subscriptionId The subscription ID of this ENSSubscriptionByCallback object.
     */
    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * 
     * @return The subscription name of this ENSSubscriptionByCallback object.
     */
    public String getSubscriptionName() {
        return subscriptionName;
    }

    /**
     * 
     * @param subscriptionName The subscription name of this ENSSubscriptionByCallback object.
     */
    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    /**
     * 
     * @return The event category types of this ENSSubscriptionByCallback object.
     */
    public List<String> getEventCategoryTypes() {
        return eventCategoryTypes;
    }

    /**
     * 
     * @param eventCategoryTypes The event category types of this ENSSubscriptionByCallback object.
     */
    public void setEventCategoryTypes(List<String> eventCategoryTypes) {
        this.eventCategoryTypes = eventCategoryTypes;
    }

    /**
     * 
     * @return The filters of this ENSSubscriptionByCallback object.
     */
    public List<String> getFilters() {
        return filters;
    }

    /**
     * 
     * @param filters The filters of this ENSSubscriptionByCallback object.
     */
    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    /**
     * 
     * @return The status of this ENSSubscriptionByCallback object.
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status The status of this ENSSubscriptionByCallback object.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return The status reason of this ENSSubscriptionByCallback object.
     */
    public String getStatusReason() {
        return statusReason;
    }

    /**
     * 
     * @param statusReason The status reason of this ENSSubscriptionByCallback object.
     */
    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    /**
     * @param <T>     The type which extends from ETRestObject
     * @param client  The ETClient object
     * @param objects The List of objects to update
     * @return The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> update(ETClient client,
            List<T> objects)
            throws ETSdkException {
        throw new ETSdkException(new IllegalStateException(
                "update method not supported for " + ENSSubscriptionByCallback.class.getName()));
    }

    /**
     * 
     * @param <T>     The type which extends from ETRestObject
     * @param client  The ETClient object
     * @param objects The List of objects to delete
     * @return The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> delete(ETClient client,
            List<T> objects)
            throws ETSdkException {
        throw new ETSdkException(
                new IllegalStateException(
                        "delete method not supported for " + ENSSubscriptionByCallback.class.getName()));
    }

    /**
     * @param <T>                   The type which extends from ETRestObject
     * @param client                The ETClient object
     * @param objects               The List of objects to create
     * @return                      The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        throw new ETSdkException(
            new IllegalStateException(
                    "create method not supported for " + ENSSubscriptionByCallback.class.getName()));
    }
}
