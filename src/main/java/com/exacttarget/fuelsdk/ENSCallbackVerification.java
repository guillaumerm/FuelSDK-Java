package com.exacttarget.fuelsdk;

import com.google.gson.annotations.Expose;

import java.util.List;

import static com.exacttarget.fuelsdk.ETRestConnection.Method.*;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/platform/v1/ens-verify", primaryKey = "callbackId", collection = "", totalCount = "")
public class ENSCallbackVerification extends ETENSRestObject {
    @Expose
    @ExternalName("callbackId")
    private String callbackId = null;
    @Expose
    @ExternalName("verificationKey")
    private String verificationKey = null;

    @Override
    public String getId() {
        return callbackId;
    }

    @Override
    public void setId(String id) {
        this.callbackId = id;
    }

    /**
     * 
     * @return The ID of the ENSCallbackVerification object.
     */
    public String getCallbackId() {
        return this.callbackId;
    }

    /**
     * 
     * @param callbackId The ID of the ENSCallbackVerification object.
     */
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    /**
     * 
     * @return The verification key of the ENSCallbackVerification object.
     */
    public String getVerificationKey() {
        return this.verificationKey;
    }

    /**
     * 
     * @param verificationKey The verification key of the ENSCallbackVerification
     *                        object.
     */
    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    /**
     * 
     * @param <T>      The type which extends from ETRestObject
     * @param client   The ETClient object
     * @param type     The class type to retrieve
     * @param page     The page number
     * @param pageSize The page size
     * @param filter   The ETFilter object
     * @return The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
            Class<T> type,
            Integer page,
            Integer pageSize,
            ETFilter filter)
            throws ETSdkException {
        // method unsupported by Salesforce shadowing to protect state of application.
        throw new ETSdkException(new IllegalStateException(
                "retrieve method not supported for " + ENSCallbackVerification.class.getName()));
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
                "update method not supported for " + ENSCallbackVerification.class.getName()));
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
                        "delete method not supported for " + ENSCallbackVerification.class.getName()));
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
        return ETENSRestObject.createUpdateDeleteSingle(client, POST, objects.get(0));
    }
}
