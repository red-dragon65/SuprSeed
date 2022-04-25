package com.cruntchy.suprseed.Engine.Lib.Images;

public interface FrameStepper {

    void moveToNextFrame();

    int getFrameStep();

    int getFrameTime();
}
