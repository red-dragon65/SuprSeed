package dev.suprseed.Engine.Core.MainView.GameProcessor.Render;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.BaseConfig;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

public class Screen {

    // TODO: Make this an observer pattern
    private float screenHeight;
    private float screenWidth;
    private float spriteScaleRatio;
    // Default target resolution (used for sprite scaling)
    private float targetResolution = 1080;
    private BaseConfig<AppCompatActivity> viewConfig;


    public void setViewConfig(BaseConfig<AppCompatActivity> viewConfig) {
        this.viewConfig = viewConfig;
    }

    /**
     * Why is this here?
     * Controls sprite scaling. If sprite images were created to fit in a resolution of 1920 by 1080,
     * specify 1080 as the target resolution width.
     *
     * @param targetResolutionWidth The reference resolution raw sprite images are targeting/scaled for.
     *                              Should be specified as a resolution width (ie, 720, 1080, 1440)
     */
    public void setTargetResolution(float targetResolutionWidth) {
        this.targetResolution = targetResolutionWidth;
    }

    // For engine
    public void setDimensions(float height, float width) {

        this.screenHeight = height;
        this.screenWidth = width;

        EngineContext.getLogger().logMessage(ErrorType.INFO, "The canvas dimensions have been set...");

        setSpriteScale();

        // Scale vertical dimension of viewport
        EngineTools.getViewPort().initScaledHeight(
                (height / width) * EngineTools.getViewPort().getWidth()
        );
    }

    private void setSpriteScale() {

        if (viewConfig != null) {

            if (viewConfig.getSetting("orientation").isPresent()) {

                if (!viewConfig.getSetting("orientation").get().active()) {

                    // For portrait
                    spriteScaleRatio = screenWidth / targetResolution;

                    return;
                }

            } else {

                String message = "Could not get the orientation setting from the ViewConfig. Defaulting to landscape view. Verify the setting ID is correct.";
                EngineContext.getLogger().logMessage(ErrorType.ERROR, message);
            }

        } else {

            String message = "The ViewConfig is null! Defaulting to landscape view!";
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);
        }

        // Default to the landscape view
        spriteScaleRatio = screenHeight / targetResolution;
    }

    // This upscales a value from 0 to 100 to the canvas' size
    // This is used for upscaling sprite coordinates to the canvas
    public float convertViewPortToCanvas(float input) {

        // Converts a given value to scale to the same point on a given canvas
        /*
        Example: sprite has x set to 25. This will be converted to (canvas width * 0.25).
         */

        return ((input / EngineTools.getViewPort().getWidth()) * screenWidth);
    }

    // This downscales a value from the canvas to 0 to 100
    // This is used to downscale an images dimensions into usable sprite coordinates
    public float convertCanvasToViewPort(float input) {

        return (input / screenWidth) * EngineTools.getViewPort().getWidth();
    }

    public float getHeight() {
        return screenHeight;
    }

    public float getWidth() {
        return screenWidth;
    }

    public float getSpriteScaleRatio() {
        return spriteScaleRatio;
    }

}
