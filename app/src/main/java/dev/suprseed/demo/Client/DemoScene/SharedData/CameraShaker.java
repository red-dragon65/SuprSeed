package dev.suprseed.demo.Client.DemoScene.SharedData;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.Camera;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.Movable;

import java.util.Random;

public class CameraShaker {

    private static Movable cameraShake;
    private final Random rand = new Random();

    // TODO: Calculate number of frames using client specified game logic/render rate
    private final int targetFrameTime = 15; // Number of frames to shake camera
    private int currentFrameTime = targetFrameTime + 1;
    private final int maxShakeDistance = 3;
    private final int shakeRate = 1;


    // Constructor
    public CameraShaker(){

        cameraShake = () -> {

            // Set camera to random point every five frames until
            if(currentFrameTime < targetFrameTime){

                if(currentFrameTime % shakeRate == 0){ // Check frame interval to run movement

                    if(currentFrameTime % 2 == 0){ // Alternate movement between the two below

                        Camera.getInstance().setxOffset(0);
                        Camera.getInstance().setyOffset(0);
                    }else{

                        Camera.getInstance().setxOffset(rand.nextInt(maxShakeDistance));
                        Camera.getInstance().setyOffset(rand.nextInt(maxShakeDistance));
                    }
                }

                currentFrameTime++;
            }

            // Force camera back to original position
            if(currentFrameTime == targetFrameTime){

                Camera.getInstance().setxOffset(0);
                Camera.getInstance().setyOffset(0);
            }
        };
    }

    public void triggerShake(){

        currentFrameTime = 0;
    }

    public void loadCameraShake(){

        Camera.getInstance().setCameraMovement(cameraShake);
    }
}
