package dev.suprseed.Engine.Core.MainView.GameProcessor.Render;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.BaseConfig;

public class CanvasData {

    // TODO: Make this an observer pattern

    // Eager loading singleton
    private static final CanvasData INSTANCE = new CanvasData();
    private final float scaledWidth = 100;
    private float originalHeight;
    private float originalWidth;
    private float scaledHeight;
    private float spriteScaleRatio;
    // Default target resolution (used for sprite scaling)
    private float targetResolution = 1080;
    private BaseConfig<AppCompatActivity> viewConfig;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private CanvasData() {

    }

    public static CanvasData getInstance() {
        return INSTANCE;
    }

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

        this.originalHeight = height;
        this.originalWidth = width;

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "The canvas dimensions have been set...");

        setSpriteScale();

        scaleDimensions();
    }

    private void setSpriteScale() {

        if (viewConfig != null) {

            if (viewConfig.getSetting("orientation").isPresent()) {

                if (!viewConfig.getSetting("orientation").get().active()) {

                    // For portrait
                    spriteScaleRatio = originalWidth / targetResolution;

                    return;
                }

            } else {

                String message = "Could not get the orientation setting from the ViewConfig. Defaulting to landscape view. Verify the setting ID is correct.";
                CentralLogger.getInstance().logMessage(ErrorType.ERROR, message);
            }

        } else {

            String message = "The ViewConfig is null! Defaulting to landscape view!";
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, message);
        }

        // Default to the landscape view
        spriteScaleRatio = originalHeight / targetResolution;
    }

    private void scaleDimensions() {

        scaledHeight = (originalHeight / originalWidth) * 100;
    }

    // This upscales a value from 0 to 100 to the canvas' size
    // This is used for upscaling sprite coordinates to the canvas
    public float formatCoordinateToCanvas(float input) {

        // Converts a given value to scale to the same point on a given canvas
        /*
        Example: sprite has x set to 25. This will be converted to (canvas width * 0.25).
         */

        return ((input / 100f) * CanvasData.getInstance().getOriginalWidth());
    }

    // This downscales a value from the canvas to 0 to 100
    // This is used to downscale an images dimensions into usable sprite coordinates
    public float formatCanvasToCoordinate(float input) {

        return (input / getOriginalWidth()) * 100;
    }

    public float getOriginalHeight() {
        return originalHeight;
    }

    public float getOriginalWidth() {
        return originalWidth;
    }

    public float getScaledHeight() {
        return scaledHeight;
    }

    public float getScaledWidth() {
        return scaledWidth;
    }

    public float getSpriteScaleRatio() {
        return spriteScaleRatio;
    }

}
