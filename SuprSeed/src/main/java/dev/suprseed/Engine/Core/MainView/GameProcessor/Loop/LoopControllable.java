package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public interface LoopControllable {

    boolean isHardPause();

    void setHardPause(boolean hardPause);

    boolean isSoftPause();

    void setSoftPause(boolean softPause);

    void toggleSoftPause();
}
