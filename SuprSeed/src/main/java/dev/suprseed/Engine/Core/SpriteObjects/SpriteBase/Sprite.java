package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import android.graphics.RectF;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Resetable;
import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;
import dev.suprseed.Engine.EngineTools;

public abstract class Sprite implements RenderableAndLayerable, Boundable, LogicRunnable, Resetable {

    // TODO: Apply SOLID to this

    // Dependency
    protected AssetBundle assetBundle;
    private float x;
    private float y;
    private float xVel;
    private float yVel;
    private boolean enabled = true;
    private boolean show = true;
    private boolean cameraRegistered = true;
    private boolean allowCollisionDiagnostic = true;
    private LayerHandler layerInfo;


    // Constructor that takes one sprite image set
    public Sprite(AssetBundle assetBundle) {

        // Dependency injection
        this.assetBundle = assetBundle;
        this.layerInfo = new LayerData();
    }

    // Constructor that takes one sprite image set
    public Sprite(AssetBundle assetBundle, int layerDepth) {

        // Dependency injection
        this.assetBundle = assetBundle;
        this.layerInfo = new LayerData(layerDepth);
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

    /**
     * Moves the sprite by applying the current x velocity to the sprites x position.
     * Uses the VelocityScaler to allow the physics to scale across different engine
     * logic rates.
     */
    public void applyXVel() {
        setX(
                getX() + (getxVel() * EngineTools.getVelocityScaler().getVelocityScaler())
        );
    }

    /**
     * Moves the sprite by applying the current x velocity to the sprites x position.
     * Uses the VelocityScaler to allow the physics to scale across different engine
     * logic rates.
     */
    public void applyYVel() {
        setY(
                getY() + (getyVel() * EngineTools.getVelocityScaler().getVelocityScaler())
        );
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


    public float getWidth() {
        return assetBundle.getSelectedAsset().getImageSet().getScaledWidth();
    }

    public float getHeight() {
        return assetBundle.getSelectedAsset().getImageSet().getScaledHeight();
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
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }

    @Override
    public void getRectF(RectF result) {

        result.left = this.getX();
        result.right = result.left + getWidth();

        result.top = this.getY();
        result.bottom = result.top + getHeight();
    }

    public boolean isAllowCollisionDiagnostic() {
        return allowCollisionDiagnostic;
    }

    public void setAllowCollisionDiagnostic(boolean allowCollisionDiagnostic) {
        this.allowCollisionDiagnostic = allowCollisionDiagnostic;
    }
}
