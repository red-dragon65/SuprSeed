package com.cruntchy.suprseed.ErrorLogger;

public class CentralLogger {

    // TODO: Make this a singleton!!!

    private static Logable logMethod;

    // Constructor
    public CentralLogger(Logable logMethod){

        CentralLogger.logMethod = logMethod;

        // Set the log depth
        logMethod.setLogDepth(2);
    }

    public static void logMessage(ErrorType errorType, String message) {

        logMethod.logMessage(errorType, message);
    }

}
