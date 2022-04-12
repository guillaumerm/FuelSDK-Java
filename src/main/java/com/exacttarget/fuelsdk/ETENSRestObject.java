package com.exacttarget.fuelsdk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.apache.log4j.Logger;

import java.util.List;

import com.exacttarget.fuelsdk.ETRestConnection.Method;
import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.annotations.RestObject;

import static com.exacttarget.fuelsdk.ETRestConnection.Method.*;

public abstract class ETENSRestObject extends ETRestObject {
    private static Logger logger = Logger.getLogger(ETENSRestObject.class);

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

    /**
     * @param <T>     The type which extends from ETENSRestObject
     * @param client  The ETClient object
     * @param objects The List of objects to create
     * @return The ETResponse object of type T which extends from ETENSRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
            List<T> objects)
            throws ETSdkException {
        return createUpdateDelete(client, POST, objects);
    }

    /**
     * @param <T>                   The type which extends from ETRestObject
     * @param client                The ETClient object
     * @param objects               The List of objects to update
     * @return                      The ETResponse object of type T which extends from ETRestObject
     */
    public static <T extends ETRestObject> ETResponse<T> update(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete(client, PUT, objects);
    }

    private static <T extends ETRestObject> ETResponse<T> createUpdateDelete(ETClient client,
            Method method,
            List<T> objects)
            throws ETSdkException {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotations = objects.get(0).getClass().getAnnotation(RestObject.class);

        assert annotations != null;

        String path = annotations.path();
        String primaryKey = annotations.primaryKey();

        logger.trace("path: " + path);
        logger.trace("primaryKey: " + primaryKey);

        //
        // There's currently no way to do this in bulk, so
        // we walk through the list of objects and create,
        // update, or delete them one at a time:
        //

        Gson gson = client.getGson();

        if (method == DELETE) {

            for (T object : objects) {
                logger.trace("DELETE " + path + "/" + object.getId());

                Response r = connection.delete(path + "/" + object.getId());

                ETResult<T> result = new ETResult<T>();
                result.setRequestId(r.getRequestId());
                if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                    result.setStatus(ETResult.Status.OK);
                } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                    result.setStatus(ETResult.Status.ERROR);
                }
                result.setResponseCode(r.getResponseCode().toString());
                result.setResponseMessage(r.getResponseMessage());

                response.addResult(result);

                object.setClient(client); // XXX
            }

        } else if (method == POST || method == PUT) {

            switch (method) {
                case POST:
                    logger.trace("POST " + path);
                    break;
                case PUT:
                    logger.trace("PUT " + path);
                    break;
                default:
                    throw new ETSdkException("invalid method: " + method);
            }

            String requestPayload = gson.toJson(objects);

            if (logger.isTraceEnabled()) {
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray = jsonParser.parse(requestPayload).getAsJsonArray();
                String jsonPrettyPrinted = gson.toJson(jsonArray);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            Response r = null;
            switch (method) {
                case POST:
                    r = connection.post(path, requestPayload);
                    break;
                case PUT:
                    r = connection.put(path, requestPayload);
                    break;
                default:
                    throw new ETSdkException("invalid method: " + method);
            }

            response.setRequestId(r.getRequestId());
            response.setResponseCode(r.getResponseCode().toString());
            response.setResponseMessage(r.getResponseMessage());

            if (r.getResponseCode() != 201) {
                return response;
            }

            String responsePayload = r.getResponsePayload();
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = jsonParser.parse(responsePayload).getAsJsonArray();
            if (logger.isTraceEnabled()) {
                String jsonPrettyPrinted = gson.toJson(jsonArray);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }
            @SuppressWarnings("unchecked")
            List<T> responseObjects = (List<T>) gson.fromJson(responsePayload, objects.getClass());
            System.out.println(responseObjects);
            for (T responseObject : responseObjects) {
                responseObject.setClient(client); // XXX

                ETResult<T> result = new ETResult<T>();
                result.setRequestId(r.getRequestId());
                if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                    result.setStatus(ETResult.Status.OK);
                } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                    result.setStatus(ETResult.Status.ERROR);
                }
                result.setResponseCode(r.getResponseCode().toString());
                result.setResponseMessage(r.getResponseMessage());

                result.setObject(responseObject);
                result.setClient(client); // XXX
                response.addResult(result);
            }

            for (T object : objects) {
                object.setClient(client); // XXX
            }

        } else {
            throw new ETSdkException("invalid method: " + method);
        }

        // XXX set overall status

        return response;
    }

    protected static <T extends ETRestObject> ETResponse<T> createUpdateDeleteSingle(ETClient client,
            Method method,
            T object)
            throws ETSdkException {
        ETResponse<T> response = new ETResponse<T>();

        if (object == null) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotations = object.getClass().getAnnotation(RestObject.class);

        assert annotations != null;

        String path = annotations.path();
        String primaryKey = annotations.primaryKey();

        logger.trace("path: " + path);
        logger.trace("primaryKey: " + primaryKey);

        //
        // There's currently no way to do this in bulk, so
        // we walk through the list of objects and create,
        // update, or delete them one at a time:
        //

        Gson gson = client.getGson();

        if (method == DELETE) {

            logger.trace("DELETE " + path + "/" + object.getId());

            Response r = connection.delete(path + "/" + object.getId());

            ETResult<T> result = new ETResult<T>();
            result.setRequestId(r.getRequestId());
            if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                result.setStatus(ETResult.Status.OK);
            } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(r.getResponseCode().toString());
            result.setResponseMessage(r.getResponseMessage());

            response.addResult(result);

            object.setClient(client); // XXX

        } else if (method == POST || method == PUT) {

            switch (method) {
                case POST:
                    logger.trace("POST " + path);
                    break;
                case PUT:
                    logger.trace("PUT " + path);
                    break;
                default:
                    throw new ETSdkException("invalid method: " + method);
            }

            String requestPayload = gson.toJson(object);
            System.out.println(requestPayload);
            if (logger.isTraceEnabled()) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(requestPayload).getAsJsonObject();
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            Response r = null;
            switch (method) {
                case POST:
                    r = connection.post(path, requestPayload);
                    break;
                case PUT:
                    r = connection.put(path, requestPayload);
                    break;
                default:
                    throw new ETSdkException("invalid method: " + method);
            }

            response.setRequestId(r.getRequestId());
            response.setResponseCode(r.getResponseCode().toString());
            response.setResponseMessage(r.getResponseMessage());

            if (r.getResponseCode() != 201) {
                return response;
            }

            String responsePayload = r.getResponsePayload();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(responsePayload).getAsJsonObject();
            if (logger.isTraceEnabled()) {
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }
            @SuppressWarnings("unchecked")
            T responseObject = (T) gson.fromJson(responsePayload, object.getClass());
            System.out.println(responseObject);

            object.setClient(client); // XXX

            responseObject.setClient(client); // XXX

            ETResult<T> result = new ETResult<T>();
            result.setRequestId(r.getRequestId());
            if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                result.setStatus(ETResult.Status.OK);
            } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(r.getResponseCode().toString());
            result.setResponseMessage(r.getResponseMessage());

            result.setObject(responseObject);
            result.setClient(client); // XXX
            response.addResult(result);

        } else {
            throw new ETSdkException("invalid method: " + method);
        }

        // XXX set overall status

        return response;
    }

}