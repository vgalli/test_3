package com.vgalli.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void testConnect() {
        Client client = new Client(5);
        ApiResponse apiResponse = client.connect();
        assertEquals("Unexpected status", apiResponse.getStatus(), 0);
    }

    @Test
    public void testConnectWithRetry() {
        Client client = new Client(5);
        client.setReturnCode(Client.SERVER_ERROR);
        ApiResponse apiResponse = client.connect();
        assertEquals("Unexpected status", apiResponse.getStatus(), Client.SERVER_ERROR);
        assertEquals("Unexpected number of retries", apiResponse.getNumRetries(), 5);
    }

    @Test
    public void testConnectWithClientError() {
        Client client = new Client(5);
        client.setReturnCode(Client.CLIENT_ERROR);
        ApiResponse apiResponse = client.connect();
        assertEquals("Unexpected status", apiResponse.getStatus(), Client.CLIENT_ERROR);
        assertEquals("Unexpected number of retries", apiResponse.getNumRetries(), 1);

    }

    @Test
    public void testConnectWithJitter() {
        Client client = new Client(5);
        client.setReturnCode(Client.BACK_OFF);
        ApiResponse apiResponse = client.connect();
        assertEquals("Unexpected status", apiResponse.getStatus(), Client.OK);
        // Expected 11 -> Max Backout set to 10, this means 10 failures one valid result.
        assertEquals("Unexpected number of retries", 11, apiResponse.getNumRetries());

    }
}
