package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;


import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

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
            loc[0] += EngineTools.getGlobalCamera().getxOffset();
            loc[1] += EngineTools.getGlobalCamera().getyOffset();
        }


        // Flip location based on canvas orientation
        // Apply origin offset
        loc = locationHandler.parseLocation(loc);

        // Format the location to the canvas
        loc[0] = EngineContext.getScreen().convertViewPortToCanvas(loc[0]);
        loc[1] = EngineContext.getScreen().convertViewPortToCanvas(loc[1]);


        return loc;
    }

}
