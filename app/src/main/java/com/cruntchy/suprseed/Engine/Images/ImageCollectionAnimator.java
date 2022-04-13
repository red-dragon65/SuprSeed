package com.cruntchy.suprseed.Engine.Images;


import android.graphics.Bitmap;
import android.util.Log;

import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderSystem;


public class ImageCollectionAnimator extends ImageCollection implements Animator{

    // This number must fit equally within the frame time
    // FPS options: 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60
    private int frameDelay = 0;
    private int currentFrameIndex = 0;
    private boolean cycledImage = false;
    private boolean enableLooping = true;
    private boolean lockLoop = false;


    // Constructor for animating images
    public ImageCollectionAnimator(BaseScene parentScene, String folderPath, float imageScale, Streamable imageStreamer, FolderParser folderParser, int fps, boolean loop) {
        super(folderPath, imageScale, imageStreamer, folderParser);

        // Register with the parent scene
        parentScene.animationRegister.registerObject(this);


        //Make sure fps fits within frame time
        if (GlobalFrameStepper.getInstance().getFrameTime() % fps != 0) {

            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            frameDelay = 60 / fps;
        }

        this.enableLooping = loop;
    }


    @Override
    public void resetLoop(){
        lockLoop = false;
        currentFrameIndex = 0;
    }


    @Override
    public Bitmap getImage() {
        return super.getIndexedImage(currentFrameIndex);
    }


    @Override
    public void generateNextFrame(){

        // Cycle to the next frame index
        if (cycledImage == false && GlobalFrameStepper.getInstance().getFrameStep() % frameDelay == 0 && lockLoop == false) {

            Log.d("ImageAnimator", "FrameStep: " + GlobalFrameStepper.getInstance().getFrameStep());
            Log.d("ImageAnimator", "FrameDelay: " + frameDelay);

            currentFrameIndex++;

            // Rollover animation frame to first frame when necessary
            if (currentFrameIndex >= imageSet.size()) {

                // Lock the animation to the last frame
                if(enableLooping == false){
                    lockLoop = true;
                    currentFrameIndex = imageSet.size() - 1;

                }else{

                    currentFrameIndex = 0;
                }
            }

            cycledImage = true;
        }else{
            cycledImage = false;
        }
    }

}
