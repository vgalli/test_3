package com.vgalli.client;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Client {
    private int low = 0;
    private int high = 1000;
    private Random random = new Random();
    private int maxRetry = 10;

    private int returnCode = OK;

    public static final int OK = 0;
    public static final int SERVER_ERROR = 1;
    public static final int CLIENT_ERROR = 2;
    public static final int BACK_OFF = 3;

    int maxBackout = 10;
    public Client(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    public ApiResponse connect() {
        int status = 0;
        String message = "OK";
        int numRetries = 1;

        for (int i = 1; i <= maxRetry; i++) {
            status = doSomething();

            if (status != 0) {


                if (i == maxRetry) {
                    message = "Max Retry reached. Stopping.";
                    numRetries = maxRetry;
                    break;
                }
                if (status == CLIENT_ERROR) {
                    message = "Client Error. Stopping.";
                    break;
                }
                try {
                    numRetries++;
                    Thread.sleep(((int) Math.round(Math.pow(2, i)) * 1000)
                            + (random.nextInt(high - low) + low));


                    if (status == BACK_OFF) {
                        message = "Server says - Backout";
                        System.out.println(message);
                        //Max backout will be used to simulate a return to normal after 10 tries
                        if (numRetries <= maxBackout) {
                            i = 0;
                            continue;
                        } else {
                            setReturnCode(OK);
                        }
                    }

                } catch (InterruptedException e) {
                    //Ignore
                }
            } else {
                break;
            }
        }
        System.out.println();
        return new ApiResponse(status, message, numRetries);
    }

    /**
     * returns returnCode. For the exercise setReturnCode will be set by tests to mimic some behavior
     *
     * @return int
     */
    public int doSomething() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String stringDate = sdf.format(Calendar.getInstance().getTime());
        System.out.println(stringDate + " doSomething() - " + resolveErrorCode(returnCode));

        return returnCode;
    }

    public String resolveErrorCode(int code) {
        String res = "";
        switch (code){
            case 0:
                res = "OK";
                break;
            case 1:
                res = "SERVER_ERROR";
                break;
            case 2:
                res = "CLIENT_ERROR";
                break;
        }
        return res;
    }
    /**
     * Set returnCode
     *
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
