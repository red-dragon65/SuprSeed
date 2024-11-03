package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import dev.suprseed.Engine.Lib.Images.FrameStepper;

public class GlobalFrameStepper implements FrameStepper {

    // Eager loading singleton
    private static final GlobalFrameStepper INSTANCE = new GlobalFrameStepper();
    // Global frame stepper
    // Tracks which frame in an animation should be in
    private final int startingFrame = 1;
    private final int frameTime = 60;
    private int frameStep = startingFrame;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private GlobalFrameStepper() {

    }

    public static GlobalFrameStepper getInstance() {
        return INSTANCE;
    }

    // Cycles through 60 frames of animation
    @Override
    public void moveToNextFrame() {

        if (frameStep <= frameTime) {

            frameStep++;
        } else {

            frameStep = startingFrame;
        }
    }

    @Override
    public int getFrameStep() {
        return frameStep;
    }

    @Override
    public int getFrameTime() {
        return frameTime;
    }
}
