package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;


import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class CoordinateProcessor implements CoordinateHandler {

    // Dependencies
    private final LocationHandler locationHandler;
    private final LocationScaler locationScaler;


    // Constructor
    public CoordinateProcessor(LocationHandler locationHandler, LocationScaler locationScaler) {

        this.locationHandler = locationHandler;
        this.locationScaler = locationScaler;
    }


    // Calculate the location a sprite should be put
    @Override
    public float[] parseLocation(Sprite sprite) {


        float[] loc = {sprite.getX(), sprite.getY()};

        if (sprite.isCameraEnabled()) {

            // Apply camera offset (this has to happen before transforming location data)
            loc[0] += Camera.getInstance().getxOffset();
            loc[1] += Camera.getInstance().getyOffset();
        }


        // Flip location based on canvas orientation
        // Apply origin offset
        loc = locationHandler.parseLocation(loc);

        // Apply multiplier to scale location for varying refresh/tick rates
        loc = locationScaler.applyLocationScale(loc);

        // Format the location to the canvas
        loc[0] = CanvasData.getInstance().formatCoordinateToCanvas(loc[0]);
        loc[1] = CanvasData.getInstance().formatCoordinateToCanvas(loc[1]);


        return loc;
    }

}
