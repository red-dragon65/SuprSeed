package com.cruntchy.suprseed.Engine.ErrorLogger;

public class CentralLogger implements Logable {

    // Eager loading singleton
    private static final CentralLogger INSTANCE = new CentralLogger();
    // Hold the log strategy
    private Logable logMethod;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private CentralLogger() {

        // Use cat logging as the default
        logMethod = new CatLogger();

        // Set the log depth
        logMethod.setLogDepth(2);
    }

    public static CentralLogger getInstance() {
        return INSTANCE;
    }

    public void changeLogMethod(Logable logMethod) {
        this.logMethod = logMethod;
    }

    @Override
    public void logMessage(ErrorType errorType, String message) {

        logMethod.logMessage(errorType, message);
    }

    @Override
    public void setLogDepth(int logDepth) {

        logMethod.setLogDepth(logDepth);
    }

}
