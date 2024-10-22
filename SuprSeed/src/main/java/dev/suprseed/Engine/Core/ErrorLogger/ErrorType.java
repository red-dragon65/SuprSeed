package dev.suprseed.Engine.Core.ErrorLogger;

public enum ErrorType {

    TRACE("TRACE"),
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL");

    private final String errorName;

    ErrorType(String errorName) {

        this.errorName = errorName;
    }

    @Override
    public String toString() {
        return errorName;
    }
}
