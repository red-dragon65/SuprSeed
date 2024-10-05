package dev.suprseed.Engine.Lib.Images;

public interface FrameStepper {

    void moveToNextFrame();

    int getFrameStep();

    int getFrameTime();
}
