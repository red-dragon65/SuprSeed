package dev.suprseed.Engine.Lib.Images;


import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.System.RenderSystem;
import dev.suprseed.Engine.Lib.AssetLoader.FPSMismatchException;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;


public class BitmapAnimation extends BitmapCollection implements Animator {

    // This number must fit equally within the frame time
    private int frameDelay = 0;
    private int currentFrameIndex = 0;
    private boolean enableLooping = true;
    private boolean lockLoop = false;
    private int targetFrameTime;
    private int frameCounter = 0;


    // Constructor for animating images
    public BitmapAnimation(BaseScene parentScene, String folderPath, float imageScale, Streamable imageStreamer, FolderParser folderParser, int fps, boolean loop, String tag) throws IOException {
        super(folderPath, imageScale, imageStreamer, folderParser, tag);

        /*
        This syncs the animation rate with the render frame time. This allows the render frame time
        to be changed without altering the visual animation speed.

        Note: if you set the this.targetFrameTime to a different value than the render target frame time,
        you can change the animation speed.

        this.targetFrameTime < RenderSystem.targetFrameTime
            -> animations run faster

        this.targetFrameTime < RenderSystem.targetFrameTime
            -> animations run slower

        It is recommended to set the fps value to get the animation speed you want. If, for whatever
        reason, you need an animation speed slower the 1 frame per second, than it would make sense
        to de-couple the this.targetFrameTime from the RenderSystem.
         */
        this.targetFrameTime = RenderSystem.getInstance().getTargetFrameTime();

        init(parentScene, fps, loop, tag);
    }

    private void init(BaseScene parentScene, int fps, boolean loop, String tag) {

        // Register with the parent scene
        parentScene.animationRegister.registerObject(this);

        // Note: the below code should never throw an exception unless the FPS enums have been messed with
        try {
            String tagInfo = "{tag == " + tag + "}";

            if (fps < 1) {
                throw new FPSMismatchException("An animations fps must be greater than 0 for animation: " + tagInfo);
            }

            //Make sure fps fits within frame time
            if (targetFrameTime % fps != 0) {
                String message = "The given animation " + tagInfo + " fps: " + fps + " does not fit within the frame time of: " + targetFrameTime;
                throw new FPSMismatchException(message);
            }

        } catch (FPSMismatchException e) {

            // Recover error by using next closest valid fps available
            fps = findValidFps(fps);

            String message = "INVALID FPS! Changing bitmap animation fps to nearest valid number: " + fps;
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, message, e);
        }

        frameDelay = targetFrameTime / fps;

        this.enableLooping = loop;
    }

    // Find the next nearest valid frame rate
    private int findValidFps(int fps) {

        // Prevent negative fps
        if (fps < 1) {
            return 1;
        }

        // Get a list of valid frame times
        ArrayList<Integer> validFrameTimes = validFrameTimeGenerator();

        int closestValidFrameTime = validFrameTimes.get(0);
        int lowestDiff = Math.abs(fps - validFrameTimes.get(0));

        // Find the closest valid frame time to the requested frame time
        for (Integer i : validFrameTimes) {

            if (Math.abs(fps - i) < lowestDiff) {
                lowestDiff = Math.abs(fps - i);
                closestValidFrameTime = i;
            }
        }

        return closestValidFrameTime;
    }

    // Generate valid frame rates for the given target frame time
    private ArrayList<Integer> validFrameTimeGenerator() {

        ArrayList<Integer> validFrameTimes = new ArrayList<>();

        for (int i = 1; i <= targetFrameTime; i++) {

            if (targetFrameTime % i == 0) {
                validFrameTimes.add(i);
            }
        }

        return validFrameTimes;
    }

    @Override
    public void resetLoop() {
        lockLoop = false;
        currentFrameIndex = 0;
    }


    @Override
    public Bitmap getImage() {
        return imageSet.get(currentFrameIndex);
    }

    @Override
    public void generateNextFrame() {

        // Cycle to the next frame index
        if (frameCounter % frameDelay == 0 && lockLoop == false) {

            currentFrameIndex++;

            // Rollover animation frame to first frame when necessary
            if (currentFrameIndex >= imageSet.size()) {

                // Lock the animation to the last frame
                if (enableLooping == false) {
                    lockLoop = true;
                    currentFrameIndex = imageSet.size() - 1;

                } else {

                    currentFrameIndex = 0;
                }
            }

            frameCounter = 0;
        }

        frameCounter++;
    }

}
