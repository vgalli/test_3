package com.vgalli.client;

/**
 * This class encapsulate a client response.
 */
public class ApiResponse {
    private int status;
    private String message;
    private int numRetries;

    public ApiResponse(int status, String message, int numRetries) {
        this.message = message;
        this.status = status;
        this.numRetries = numRetries;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumRetries() {
        return numRetries;
    }

    public void setNumRetries(int numRetries) {
        this.numRetries = numRetries;
    }
}
