package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;


import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class CoordinateProcessor implements CoordinateHandler {

    // Dependencies
    private final LocationHandler locationHandler;


    // Constructor
    public CoordinateProcessor(LocationHandler locationHandler) {

        this.locationHandler = locationHandler;
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

        // Format the location to the canvas
        loc[0] = Screen.getInstance().convertViewPortToCanvas(loc[0]);
        loc[1] = Screen.getInstance().convertViewPortToCanvas(loc[1]);


        return loc;
    }

}
