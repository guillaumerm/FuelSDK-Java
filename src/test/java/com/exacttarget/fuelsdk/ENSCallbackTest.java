/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

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

    private ETAsset asset = null;
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
    
}
