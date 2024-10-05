package dev.suprseed.Engine.Core.MainView.EngineSettings;

public interface Configurable<T> {

    void applySettings(T inputObject);

    boolean active();

    String getId();
}
