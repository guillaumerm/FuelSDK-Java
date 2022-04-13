/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.UUID;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ENSSubscriptionByCallbackTest {
    private static ETClient client = null;
    private static String unique = "";

    private ENSSubscriptionByCallback subscriptionByCallback = null;
    private String aid = "";

    public ENSSubscriptionByCallbackTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }

    @Test
    public void getAllSubscriptionsForCallback() throws ETSdkException {
        ETResponse<ENSSubscriptionByCallback> response = client.retrieve(ENSSubscriptionByCallback.class, "callbackId=" + aid);
        System.out.println("resp=" + response.toString());
        assertEquals(response.getResponseCode(), "200");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ENSSubscriptionByCallback> results = response.getResult();
        if (results != null) {
            System.out.println("res=" + results.toString());
            assertNotNull(results.getObject());
        }
    }

    @Test
    public void getCallbackVerification() throws ETSdkException {
        try {
            client.retrieve(ENSCallbackVerification.class, "id=" + aid);
            fail();
        } catch (ETSdkException ex) {
            assertEquals(new ETSdkException(new IllegalStateException(
                    "retrieve method not supported for " + ENSCallbackVerification.class.getName())).getMessage(), ex.getCause().getCause().getMessage());
        }
    }
}
