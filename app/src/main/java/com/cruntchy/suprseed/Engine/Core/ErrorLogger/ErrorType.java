package com.cruntchy.suprseed.Engine.Core.ErrorLogger;

public enum ErrorType {

    INFO("INFO"),
    WARNING("WARNING"),
    CONFIG_ERROR("CONFIG_ERROR"),
    FATAL_ERROR("FATAL_ERROR");

    private final String errorName;

    ErrorType(String errorName) {

        this.errorName = errorName;
    }

    @Override
    public String toString() {
        return errorName;
    }
}
