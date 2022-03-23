package com.cruntchy.suprseed.Engine.Images;

public class GlobalFrameStepper {

    // TODO: Make this a proper singleton

    // Global frame stepper
    // Tracks which frame in an animation should be in
    private static final int startingFrame = 1;
    private static int frameStep = startingFrame;
    private static final int frameTime = 60;


    // Cycles through 60 frames of animation
    public static void moveToNextFrame(){


        //Log.d("ImageAnimator", "NEXT FRAME QUEUED!");

        if(frameStep <= frameTime){

            frameStep++;
        }else{

            frameStep = startingFrame;
        }

    }


    public static int getFrameStep() {
        return frameStep;
    }

    public static int getFrameTime() {
        return frameTime;
    }
}
