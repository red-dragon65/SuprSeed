package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;


import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class CoordinateProcessor implements CoordinateHandler {


    // Dependencies
    private LocationHandler locationHandler;
    private LocationScaler locationScaler;
    private Camera camera;



    // Constructor
    public CoordinateProcessor(LocationHandler locationHandler, LocationScaler locationScaler){

        this.locationHandler = locationHandler;
        this.locationScaler = locationScaler;
    }


    // Calculate the location a sprite should be put
    @Override
    public float[] parseLocation(Sprite sprite, float canvasWidth, float canvasHeight){


        float[] loc = {sprite.getX(), sprite.getY()};

        if(sprite.isCameraEnabled()){

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
        loc[0] = locationScaler.formatCoordinateToCanvas(loc[0], canvasWidth);
        loc[1] = locationScaler.formatCoordinateToCanvas(loc[1], canvasWidth);




        return loc;
    }


}
