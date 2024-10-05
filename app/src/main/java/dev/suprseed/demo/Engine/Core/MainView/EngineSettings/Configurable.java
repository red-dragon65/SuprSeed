package dev.suprseed.demo.Engine.Core.MainView.EngineSettings;

public interface Configurable<T> {

    void applySettings(T inputObject);

    boolean active();

    String getId();
}
