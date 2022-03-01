package com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Renderable;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.SpriteSystem;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Systemizable;

public abstract class Sprite implements Renderable {

    private float x;
    private float y;

    private float xVel;
    private float yVel;

    private boolean enabled = true;
    private boolean show = true;

    private boolean cameraRegistered = true;

    // Dependency
    private ImageHandler imageHandler;
    protected Systemizable spriteSystem;


    // Constructor that takes one sprite image set
    protected Sprite(ImageHandler imageHandler) {

        // Dependency injection
        this.imageHandler = imageHandler;

        spriteSystem = SpriteSystem.getInstance();

        // Register this renderable
        spriteSystem.registerRenderSprite(this);
    }



    @Override
    public void draw(RenderHandler renderer){

        renderer.drawSprite(this);
    }



    // Getters / setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public ImageHandler getImageHandler() {
        return imageHandler;
    }

    public void setImageHandler(ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }

    public void enableCamera(){
        cameraRegistered = true;
    }

    public void disableCamera(){
        cameraRegistered = false;
    }

    public boolean isCameraEnabled(){
        return cameraRegistered;
    }

}
