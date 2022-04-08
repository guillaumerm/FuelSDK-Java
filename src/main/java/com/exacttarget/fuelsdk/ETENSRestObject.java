package com.exacttarget.fuelsdk;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.annotations.RestObject;

public abstract class ETENSRestObject extends ETRestObject {
    private static Logger logger = Logger.getLogger(ETENSRestObject.class);
        /**
     * 
     * @param <T>           The type which extends from ETRestObject
     * @param client        The ETClient object
     * @param type          The class type to retrieve
     * @param page          The page number
     * @param pageSize      The page size
     * @param filter        The ETFilter object
     * @return              The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotations = type.getAnnotation(RestObject.class);

        assert annotations != null;

        String path = annotations.path();
        String primaryKey = annotations.primaryKey();

        logger.trace("path: " + path);
        logger.trace("primaryKey: " + primaryKey);

        //
        // Build the query parameters:
        //

        StringBuilder stringBuilder = new StringBuilder(path);

        path = stringBuilder.toString();

        logger.trace("GET " + path);

        Response r = connection.get(path);

        response.setRequestId(r.getRequestId());
        if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
            response.setStatus(ETResult.Status.OK);
        } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(r.getResponseCode().toString());
        response.setResponseMessage(r.getResponseMessage());

        Gson gson = client.getGson();
        
        JsonParser jsonParser = new JsonParser();
       
        try {
            JsonArray elements = jsonParser.parse(r.getResponsePayload()).getAsJsonArray();
            for (JsonElement element : elements) {
                T object = gson.fromJson(element, type);
                object.setClient(client);
                ETResult<T> result = new ETResult<T>();
                result.setObject(object);
                response.addResult(result);
            }
        } catch (JsonSyntaxException ex) {
            T object = gson.fromJson(r.getResponsePayload(), type);
            object.setClient(client); // XXX
            ETResult<T> result = new ETResult<T>();
            result.setObject(object);
            response.addResult(result);
        }

        return response;
    }
}
