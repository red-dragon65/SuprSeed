package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.Engine.Core.System.Logic;
import dev.suprseed.Engine.Core.System.LogicSystem;

public class Camera implements Logic {

    private float xOffset;
    private float yOffset;

    private float xVelocity;
    private float yVelocity;

    // Modifiable behavior
    private Movable cameraMovement;

    // Eager loading singleton
    private static final Camera INSTANCE = new Camera();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private Camera() {

        // Register with the sprite system
        LogicSystem.getInstance().registerObject(this);

        // Default movement behavior
        cameraMovement = () -> {

            // Update the cameras location based on velocity
            setxOffset(getxOffset() + getxVelocity());
            setyOffset(getyOffset() + getyVelocity());
        };
    }

    public static Camera getInstance() {
        return INSTANCE;
    }


    @Override
    public void runLogic() {

        cameraMovement.move();
    }

    @Override
    public boolean isActive() {
        return true;
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
