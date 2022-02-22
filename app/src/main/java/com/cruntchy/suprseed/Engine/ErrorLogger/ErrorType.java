package com.cruntchy.suprseed.Engine.ErrorLogger;

public enum ErrorType {

    INFO("INFO"),
    WARNING("WARNING"),
    CONFIG_ERROR("CONFIG_ERROR"),
    FATAL_ERROR("FATAL_ERROR");

    private ErrorType(String errorName){

        this.errorName = errorName;
    }

    private String errorName;

    @Override
    public String toString() {
        return errorName;
    }
}
