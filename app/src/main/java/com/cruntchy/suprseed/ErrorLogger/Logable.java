package com.cruntchy.suprseed.ErrorLogger;

public interface Logable {

    public void logMessage(ErrorType errorType, String message);

    public void setLogDepth(int logDepth);
}
