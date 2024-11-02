package dev.suprseed.Engine.Lib.Input.Dispatchers;

import androidx.annotation.NonNull;

public enum TouchTypes {

    TAP("tap"),
    HOLD("hold"),
    DRAG("drag"),
    LIFT("lift");

    String value = "";

    TouchTypes(String value) {
        this.value = value;
    }

    @NonNull
    public String toString() {
        return value;
    }

    public boolean equals(String given) {
        return given.equals(value);
    }
}
