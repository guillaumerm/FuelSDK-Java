/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.exacttarget.fuelsdk.ENSSubscription.NotificationEventType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ENSSubscriptionTest {
    private static ETClient client = null;
    private static String unique = "";

    private ENSSubscription subscription = null;
    private String aid = "90840";

    public ENSSubscriptionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }

    @Test
    public void createSubscription() throws ETSdkException
    {
        try {
            String callbackId = "";
            String subscriptionName = "JavaSDK";
            List<NotificationEventType> eventTypeCategories = new ArrayList<NotificationEventType>();
            eventTypeCategories.add(NotificationEventType.EMAIL_NOT_SENT);
            subscription = new ENSSubscription();
            subscription.setCallbackId(callbackId);
            subscription.setSubscriptionName(subscriptionName);
            subscription.setEventCategoryTypes(eventTypeCategories);
            List<ENSSubscription> subscriptions = new ArrayList<ENSSubscription>();
            subscriptions.add(subscription);
            ETResponse<ENSSubscription> response = client.create(subscriptions);
            System.out.println("resp="+ response.toString());
            List<ETResult<ENSSubscription>> results = response.getResults();
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }
}
