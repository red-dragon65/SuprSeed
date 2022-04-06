package com.cruntchy.suprseed.Engine.Images;

public interface FrameStepper {

    void moveToNextFrame();

    int getFrameStep();

    int getFrameTime();
}
