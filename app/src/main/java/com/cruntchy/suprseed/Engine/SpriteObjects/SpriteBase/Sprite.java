package com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.Collisions.Boundable;
import com.cruntchy.suprseed.Engine.Collisions.CollisionDiagnosticsOverlay;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderableAndLayerable;

public abstract class Sprite implements RenderableAndLayerable, Boundable, Logic {

    // TODO: Apply SOLID to this

    private float x;
    private float y;

    private float xVel;
    private float yVel;

    private boolean enabled = true;
    private boolean show = true;

    private boolean cameraRegistered = true;

    private int layerDepth = 0;

    // Dependency
    protected ImageHandler imageHandler;
    protected BaseScene parentScene;


    // Constructor that takes one sprite image set
    public Sprite(BaseScene parentScene, ImageHandler imageHandler) {

        // Dependency injection
        this.imageHandler = imageHandler;
        this.parentScene = parentScene;


        // Register this to the scene
        parentScene.imageRegister.registerObject(this);
        parentScene.logicRegister.registerObject(this);
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




    @Override
    public boolean isDrawable(){
        return show;
    }
    public void setShow(boolean show) {
        this.show = show;
    }



    public boolean isActive(){
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public void disableCamera() {
        cameraRegistered = false;
    }

    public boolean isCameraEnabled() {
        return cameraRegistered;
    }

    @Override
    public int getLayerDepth() {
        return layerDepth;
    }

    public void setLayerDepth(int layerDepth) {
        this.layerDepth = layerDepth;
    }

    public float getCanvasScaledWidth() {
        return CanvasData.getInstance().getScaledWidth();
    }

    public float getCanvasScaledHeight() {
        return CanvasData.getInstance().getScaledHeight();
    }

    @Override
    public void getRectF(RectF result) {

        result.left = CanvasData.getInstance().formatCoordinateToCanvas(this.getX());
        result.right = result.left + this.getImageHandler().getSelectedImageSet().getImage().getWidth();

        result.top = CanvasData.getInstance().formatCoordinateToCanvas(this.getY());
        result.bottom = result.top + this.getImageHandler().getSelectedImageSet().getImage().getHeight();

        CollisionDiagnosticsOverlay.getInstance().addRect(result);
    }
}
