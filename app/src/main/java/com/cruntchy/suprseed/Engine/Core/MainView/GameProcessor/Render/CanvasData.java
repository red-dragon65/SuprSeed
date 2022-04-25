package com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render;

import com.cruntchy.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.Core.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;

public class CanvasData {

    // TODO: Make this an observer pattern

    private final float scaledWidth = 100;
    private float originalHeight;
    private float originalWidth;
    private float scaledHeight;
    private float spriteScaleRatio;

    // Default target resolution
    private float targetResolution = 1080;

    private final LocationTemporalScaler scaler = new LocationTemporalScaler();

    // Eager loading singleton
    private static final CanvasData INSTANCE = new CanvasData();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private CanvasData() {

    }

    public static CanvasData getInstance() {
        return INSTANCE;
    }

    // For client
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

        ViewConfig viewy = new ViewConfig();

        if (viewy.getSetting("orientation").active()) {

            // For landscape
            spriteScaleRatio = originalHeight / targetResolution;
        } else {

            // For portrait
            spriteScaleRatio = originalWidth / targetResolution;
        }
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
        return scaledHeight / scaler.getLocationScaleRatio();
    }

    public float getScaledWidth() {
        return scaledWidth / scaler.getLocationScaleRatio();
    }

    public float getSpriteScaleRatio() {
        return spriteScaleRatio;
    }

}
