package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;


public class CoordinateProcessor implements CoordinateHandler {


    // Dependencies
    private LocationHandler locationHandler;
    private LocationScaler locationScaler;
    private Camera camera;



    // Constructor
    public CoordinateProcessor(){

    }


    // Calculate the location a sprite should be put
    @Override
    public float[] parseLocation(float[] loc){

        /*

        // Apply camera offset (this has to happen before transforming location data)
        loc[0] += camera.getX();
        loc[1] += camera.getY();



        // Flip location based on canvas orientation
        // Apply origin offset
        loc = locationHandler.parseLocation(loc);

        // Apply multiplier to scale location for varying refresh/tick rates
        loc = locationScaler.applyLocationScale(loc);

        // Format the location to the canvas
        loc[0] = locationScaler.formatCoordinateToCanvas(loc[0], canvasWidth);
        loc[1] = locationScaler.formatCoordinateToCanvas(loc[1], canvasHeight);

         */


        return loc;
    }


}
