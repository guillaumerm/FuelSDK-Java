/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ENSCallbackTest {
    private static ETClient client = null;
    private static String unique = "";

    private ENSCallback callback = null;
    private String aid = "90840";

    public ENSCallbackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }
    
    @Test
    public void getAllCallbacks() throws ETSdkException
    {
        ETResponse<ENSCallback> response = client.retrieve(ENSCallback.class);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "200");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());         
        
        ETResult<ENSCallback> result = response.getResult();
        if (result != null) {
            System.out.println("res="+ result.toString());
            assertNotNull(result.getObject());
        }
    }

    @Test
    public void getCallback()  throws ETSdkException
    {
        ETResponse<ENSCallback> response = client.retrieve(ENSCallback.class, "id="+aid);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "200");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ENSCallback> result = response.getResult();
        if (result != null) {
            System.out.println("res="+ result.toString());
            assertEquals(result.getObjectId(), result);
        }
    }

    @Test
    public void createCallback() throws ETSdkException
    {
        try {
            String callbackName = "JavaSDK";
            int maxBatchSize = 100;
            String url = "https://inspector.guillaumerm.dev/inspect";

            callback = new ENSCallback();
            callback.setCallbackName(callbackName);
            callback.setUrl(url);
            callback.setMaxBatchSize(maxBatchSize);
            List<ENSCallback> callbacks = new ArrayList<ENSCallback>();
            callbacks.add(callback);
            ETResponse<ENSCallback> response = client.create(callbacks);
            System.out.println("resp="+ response.toString());
            List<ETResult<ENSCallback>> results = response.getResults();
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void deleteCallback() throws ETSdkException
    {
        try {
            String callbackId = "5954e2a7-b225-47bf-9d62-face89568cf9";
            callback = new ENSCallback();
            callback.setId(callbackId);

            ETResponse<ENSCallback> response = client.delete(callback);

            ETResult<ENSCallback> result = response.getResult();

            assertEquals(result.getResponseCode(), "200");
            assertEquals(result.getResponseMessage(), "OK");
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void updateCallback() throws ETSdkException
    {
        try {
            String callbackId = "5954e2a7-b225-47bf-9d62-face89568cf9";
            String callbackName = "JavaSDK (Updated)";
            int maxBatchSize = 100;
            String url = "https://inspector.guillaumerm.dev/inspect";

            callback = new ENSCallback();
            callback.setCallbackId(callbackId);
            callback.setCallbackName(callbackName);
            callback.setMaxBatchSize(maxBatchSize);
            callback.setUrl(url);

            List<ENSCallback> callbacks = new ArrayList<ENSCallback>();
            callbacks.add(callback);

            ETResponse<ENSCallback> response = client.update(callbacks);

            List<ETResult<ENSCallback>> results = response.getResults();
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }
}
