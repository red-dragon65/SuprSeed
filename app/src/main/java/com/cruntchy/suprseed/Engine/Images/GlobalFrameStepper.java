package com.cruntchy.suprseed.Engine.Images;

import android.util.Log;

public class GlobalFrameStepper {

    // TODO: Make this a singleton
    // TODO: Make this a part of a system
    // TODO: Call this within a loop (loop config)

    // Global frame stepper
    // Tracks which frame in an animation should be in
    private static final int startingFrame = 1;
    private static int frameStep = startingFrame;
    private static final int frameTime = 60;


    // Cycles through 60 frames of animation
    public static void moveToNextFrame(){


        Log.d("ImageAnimator", "NEXT FRAME QUEUED!");

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
