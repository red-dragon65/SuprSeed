package dev.suprseed.Engine.Lib.Images;


import android.graphics.Bitmap;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Lib.AssetLoader.FPSMismatchException;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;


public class BitmapAnimation extends BitmapCollection implements Animator {

    // This number must fit equally within the frame time
    private int frameDelay = 0;
    private int currentFrameIndex = 0;
    private boolean cycledImage = false;
    private boolean enableLooping = true;
    private boolean lockLoop = false;


    // Constructor for animating images
    public BitmapAnimation(BaseScene parentScene, String folderPath, float imageScale, Streamable imageStreamer, FolderParser folderParser, FPS fps, boolean loop, String tag) {
        super(folderPath, imageScale, imageStreamer, folderParser, tag);

        // Register with the parent scene
        parentScene.animationRegister.registerObject(this);

        // Note: the below code should never throw an exception unless the FPS enums have been messed with
        try{
            String tagInfo = "{tag == " + tag + "}";

            if(fps.toInt() < 1){
                throw new FPSMismatchException("An animations fps must be greater than 0 for animation: " + tagInfo);
            }

            //Make sure fps fits within frame time
            if (GlobalFrameStepper.getInstance().getFrameTime() % fps.toInt() != 0) {
                String message = "The given animation " + tagInfo + " fps: " + fps + " does not fit within the frame time of: " + GlobalFrameStepper.getInstance().getFrameTime();
                throw new FPSMismatchException(message);
            }

        }catch(FPSMismatchException e){

            // Recover error by using next closest valid fps available
            fps = findValidFps(fps.toInt());

            String message = "INVALID FPS! Changing bitmap animation fps to nearest valid number: " + fps;
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, message, e);
        }

        frameDelay = 60 / fps.toInt();

        this.enableLooping = loop;
    }

    private FPS findValidFps(int fps){

        if(fps < 1){
            return FPS._1;
        }

        int lowFps = fps;
        int highFps = fps;
        int newTarget;
        int numTries = 10;

        // Find next properly divisible number
        for(int i = 0; i < numTries; i++){

            if(GlobalFrameStepper.getInstance().getFrameTime() % lowFps != 0){
                lowFps--;
            }

            if(GlobalFrameStepper.getInstance().getFrameTime() % highFps != 0){
                highFps++;
            }
        }

        // See if higher or lower number is closer to users original fps
        if((fps - lowFps) < (highFps - fps)){
            newTarget = lowFps;
        }else{
            newTarget = highFps;
        }

        for(FPS i : FPS.values()){
            if(i.toInt() == newTarget){
                return i;
            }
        }

        return FPS._1;
    }

    @Override
    public void resetLoop() {
        lockLoop = false;
        currentFrameIndex = 0;
    }


    @Override
    public Bitmap getImage() {
        return super.getIndexedImage(currentFrameIndex);
    }


    @Override
    public void generateNextFrame() {

        // Cycle to the next frame index
        if (cycledImage == false && GlobalFrameStepper.getInstance().getFrameStep() % frameDelay == 0 && lockLoop == false) {

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

            cycledImage = true;
        } else {
            cycledImage = false;
        }
    }

}
