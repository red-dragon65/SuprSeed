package dev.suprseed.demo.SharedData;

import java.util.Random;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.Movable;
import dev.suprseed.Engine.EngineTools;

public class CameraShaker {

    private static Movable cameraShake;
    private final Random rand = new Random();

    // TODO: Calculate number of frames using client specified game logic/render rate
    private final int targetFrameTime = 15; // Number of frames to shake camera
    private final int maxShakeDistance = 3;
    private final int shakeRate = 1;
    private int currentFrameTime = targetFrameTime + 1;
    private boolean resetCamera = false;


    // Constructor
    public CameraShaker() {

        cameraShake = () -> {

            // Set camera to random point every five frames until
            if (currentFrameTime < targetFrameTime) {

                if (currentFrameTime % shakeRate == 0) { // Check frame interval to run movement

                    resetCamera = true;

                    // TODO: Fix this bug
                    //      when logic runs twice per frame, the camera will not show movement!
                    if (currentFrameTime % 2 == 0) { // Alternate movement between the two below

                        EngineTools.getGlobalCamera().setxOffset(0);
                        EngineTools.getGlobalCamera().setyOffset(0);
                    } else {

                        EngineTools.getGlobalCamera().setxOffset(rand.nextInt(maxShakeDistance));
                        EngineTools.getGlobalCamera().setyOffset(rand.nextInt(maxShakeDistance));
                    }
                }

                currentFrameTime++;
            }

            // Set camera back to original position
            if (currentFrameTime == targetFrameTime && resetCamera) {

                EngineTools.getGlobalCamera().setxOffset(0);
                EngineTools.getGlobalCamera().setyOffset(0);
                resetCamera = false;
            }
        };
    }

    public void triggerShake() {

        currentFrameTime = 0;
    }

    public void loadCameraShake() {

        EngineTools.getGlobalCamera().setCameraMovement(cameraShake);
    }
}
