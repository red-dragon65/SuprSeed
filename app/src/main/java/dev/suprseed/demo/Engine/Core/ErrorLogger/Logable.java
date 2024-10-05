package dev.suprseed.demo.Engine.Core.ErrorLogger;

public interface Logable {

    void logMessage(ErrorType errorType, String message);

    void setLogDepth(int logDepth);
}
