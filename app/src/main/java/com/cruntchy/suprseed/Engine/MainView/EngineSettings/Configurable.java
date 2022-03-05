package com.cruntchy.suprseed.Engine.MainView.EngineSettings;

public interface Configurable<T> {

    void applySettings(T inputObject);

    boolean active();

    String getId();
}
