package dev.suprseed.demo.Engine.Lib.Images;

public interface FrameStepper {

    void moveToNextFrame();

    int getFrameStep();

    int getFrameTime();
}
