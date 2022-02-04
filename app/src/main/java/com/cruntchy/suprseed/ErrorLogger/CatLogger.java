package com.cruntchy.suprseed.ErrorLogger;

import android.util.Log;

public class CatLogger implements Logable {

    private String applicationName = "Cruntchy";
    private String topStackTrace = "";
    private String tag = "";
    private String postMessage = "";


    public void logMessage(ErrorType errorType, String message){

        // TODO: Verify that this is working as intended

        // Build the log data

        topStackTrace = "Internal stack trace: ";

        // Get the stack trace of the caller of this method
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        topStackTrace = stackTrace[1].getClassName();
        topStackTrace += "::";
        topStackTrace += stackTrace[1].getMethodName();

        tag = applicationName + "::" + errorType.toString();



        // Log message using correct logging level
        switch(errorType){
            case INFO:
                Log.i(tag, topStackTrace);
                Log.i(tag, message);
                break;
            case WARNING:
                Log.w(tag, topStackTrace);
                Log.w(tag, message);
                break;
            case CONFIG_ERROR:
                Log.e(tag, topStackTrace);
                Log.e(tag, message);
                break;
            case FATAL_ERROR:
                Log.wtf(tag, topStackTrace);
                Log.wtf(tag, message);
                break;
        }
    }
}