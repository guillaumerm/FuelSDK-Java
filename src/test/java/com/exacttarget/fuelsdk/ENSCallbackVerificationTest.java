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
public class ENSCallbackVerificationTest {
    private static ETClient client = null;
    private static String unique = "";

    private ENSCallbackVerification callbackVerification = null;
    private String aid = "90840";

    public ENSCallbackVerificationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }

    @Test
    public void getAllCallbackVerifications() throws ETSdkException {
        try {
            client.retrieve(ENSCallbackVerification.class);
            fail();
        } catch (ETSdkException ex) {
            assertEquals(new ETSdkException(new IllegalStateException(
                    "retrieve method not supported for " + ENSCallbackVerification.class.getName())).getMessage(), ex.getCause().getCause().getMessage());
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

    @Test
    public void createCallbackVerification() throws ETSdkException {
        try {
            String callbackId = "";
            String verificationKey = "";

            callbackVerification = new ENSCallbackVerification();
            callbackVerification.setCallbackId(callbackId);
            callbackVerification.setVerificationKey(verificationKey);
            ETResponse<ENSCallbackVerification> response = client.create(callbackVerification);
            System.out.println("resp=" + response.toString());
            ETResult<ENSCallbackVerification> result = response.getResult();
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void deleteCallbackVerification() throws ETSdkException {
        try {
            callbackVerification = new ENSCallbackVerification();
            client.delete(callbackVerification);
            fail();
        } catch (ETSdkException ex) {
            assertEquals(new ETSdkException(new IllegalStateException(
                    "delete method not supported for " + ENSCallbackVerification.class.getName())).getMessage(), ex.getCause().getCause().getMessage());
        }
    }

    @Test
    public void updateCallbackVerification() throws ETSdkException {
        try {
            callbackVerification = new ENSCallbackVerification();
            client.update(callbackVerification);
            fail();
        } catch (ETSdkException ex) {
            assertEquals(new ETSdkException(new IllegalStateException(
                    "update method not supported for " + ENSCallbackVerification.class.getName())).getMessage(), ex.getCause().getCause().getMessage());
        }
    }
}
