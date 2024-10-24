package dev.suprseed.Engine.Core.ErrorLogger;

import android.util.Log;

public class AndroidCatLogger implements Logable {

    private final String applicationName = "SuprSeed";
    private String tag = "";
    private boolean debugMode = false;

    @Override
    public void logMessage(ErrorType errorType, String message, Exception e) {

        // Build the log data
        if (debugMode) {

            String topStackTrace = "Internal stack trace: ";

            // Get the stack trace of the caller of this method
            // Note: getting the stack trace is expensive! Disable debug for production use cases.
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            topStackTrace += stackTrace[3].getClassName();
            topStackTrace += "::";
            topStackTrace += stackTrace[3].getMethodName() + "()";

            message += "    [" + topStackTrace + "]";
        }

        tag = applicationName + "::" + errorType.toString();

        // Log message using correct logging level
        switch (errorType) {
            case TRACE:
                if (e != null) {
                    Log.v(tag, message, e);
                } else {
                    Log.v(tag, message);
                }
                break;
            case DEBUG:
                if (e != null) {
                    Log.d(tag, message, e);
                } else {
                    Log.d(tag, message);
                }
                break;
            case INFO:
                if (e != null) {
                    Log.i(tag, message, e);
                } else {
                    Log.i(tag, message);
                }
                break;
            case WARN:
                if (e != null) {
                    Log.w(tag, message, e);
                } else {
                    Log.w(tag, message);
                }
                break;
            case ERROR:
                if (e != null) {
                    Log.e(tag, message, e);
                } else {
                    Log.e(tag, message);
                }
                break;
            case FATAL:
                if (e != null) {
                    Log.wtf(tag, message, e);
                } else {
                    Log.wtf(tag, message);
                }
                break;
        }
    }

    @Override
    public void logMessage(ErrorType errorType, String message) {
        logMessage(errorType, message, null);
    }

    @Override
    public void setDebugMode(boolean isEnabled) {
        debugMode = isEnabled;
    }
}
