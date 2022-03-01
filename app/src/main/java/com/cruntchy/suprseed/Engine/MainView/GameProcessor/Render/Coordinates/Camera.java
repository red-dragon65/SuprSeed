package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.SpriteSystem;

public class Camera implements Movable {

    // TODO: make this a singleton!

    private float xOffset;
    private float yOffset;

    private float xVelocity;
    private float yVelocity;

    // Modifiable behavior
    private Movable cameraMovement;


    // Constructor
    public Camera(){

        // Register with the sprite system
        SpriteSystem.getInstance().registerMovingSprite(this);
    }


    // VERIFY: is this correct?
    private static class CameraSingleton {
        private static final Camera INSTANCE = new Camera();
    }

    // VERIFY: is this correct?
    public static Camera getInstance() {
        return Camera.CameraSingleton.INSTANCE;
    }




    @Override
    public void move() {

        // Use default behavior or switch to user specified behavior
        if(cameraMovement == null){

            // Update the cameras location based on velocity
            setxOffset(getxOffset() + getxVelocity());
            setyOffset(getyOffset() + getyVelocity());

        }else{

            cameraMovement.move();
        }
    }




    // Getters/setters
    public Movable getCameraMovement() {
        return cameraMovement;
    }

    public void setCameraMovement(Movable cameraMovement) {
        this.cameraMovement = cameraMovement;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }


}
