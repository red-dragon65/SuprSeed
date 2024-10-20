package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import android.graphics.RectF;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Resetable;
import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;
import dev.suprseed.Engine.Core.System.Registerables.IRenderableAndILayerable;
import dev.suprseed.Engine.Lib.Collisions.Boundable;

public abstract class Sprite implements IRenderableAndILayerable, Boundable, ILogicRunnable, Resetable {

    // TODO: Apply SOLID to this

    // Dependency
    protected AssetBundle assetBundle;
    protected BaseScene parentScene;
    private float x;
    private float y;
    private float xVel;
    private float yVel;
    private boolean enabled = true;
    private boolean show = true;
    private boolean cameraRegistered = true;
    private int layerDepth = 0;
    private boolean allowCollisionDiagnostic = true;


    // Constructor that takes one sprite image set
    public Sprite(BaseScene parentScene, AssetBundle assetBundle) {

        // Dependency injection
        this.assetBundle = assetBundle;
        this.parentScene = parentScene;


        // Register this to the scene
        parentScene.imageRegister.registerObject(this);
        parentScene.logicRegister.registerObject(this);
    }


    @Override
    public void draw(RenderHandler renderer) {
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
    public boolean isDrawable() {
        return show;
    }

    public void setDrawable(boolean show) {
        this.show = show;
    }


    public boolean isActive() {
        return enabled;
    }

    public void setActive(boolean enabled) {
        this.enabled = enabled;
    }


    public void resetState() {

    }


    public AssetBundle getAssetBundle() {
        return assetBundle;
    }

    public void setImageHandler(AssetBundle assetBundle) {
        this.assetBundle = assetBundle;
    }

    public void enableCamera() {
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
        result.right = result.left + this.getAssetBundle().getSelectedImageSet().getImage().getWidth();

        result.top = CanvasData.getInstance().formatCoordinateToCanvas(this.getY());
        result.bottom = result.top + this.getAssetBundle().getSelectedImageSet().getImage().getHeight();
    }

    public boolean isAllowCollisionDiagnostic() {
        return allowCollisionDiagnostic;
    }

    public void setAllowCollisionDiagnostic(boolean allowCollisionDiagnostic) {
        this.allowCollisionDiagnostic = allowCollisionDiagnostic;
    }
}
