package com.cruntchy.suprseed.MainView.GameProcessor.Render;

import com.cruntchy.suprseed.ErrorLogger.Logable;

public class CoordinateHandler {

    // Used to calculate the correct position of a sprites location
    // Ensures that varying refresh rates and tick rates keep consistent
    // sprite movement
    private int locationScaleRatio;

    // Dependencies
    private Logable logger;

    private boolean isPortrait;
    private boolean isTopLeftOrigin;



    // Constructor
    public CoordinateHandler(Logable logger, boolean isPortrait, boolean isTopLeftOrigin){

        // Injection
        this.logger = logger;

        this.isPortrait = isPortrait;
        this.isTopLeftOrigin = isTopLeftOrigin;


        // Initialize screen orientation offset
        if(isPortrait){
            setCanvasToPortrait();
        }else{
            setCanvasToLandscape();
        }


        // Initialize coordinate origin offset
        if(isTopLeftOrigin){
            makeTopLeftOrigin();
        }else{
            makeCenterOrigin();
        }

    }




    // Flips x and y value of sprites to ensure they match screen orientation
    private void setCanvasToPortrait(){

        // DEFAULT VALUES
    }

    // Flips x and y value of sprites to ensure they match screen orientation
    private void setCanvasToLandscape(){

        // TODO: Finish this!
    }



    // Makes the top left of the screen 0 for x and y
    private void makeTopLeftOrigin(){

        // DEFAULT VALUES

    }

    // Makes the center of the screen 0 for x and y
    private void makeCenterOrigin(){

        // TODO: Finish this!
    }




    public float formatCoordinateToCanvas(float input, float canvasSize){

        // Converts a given value to scale to the same point on a given canvas
        /*
        Example: sprite has x set to 25. This will be converted to (canvas width * 0.25).
         */

        return ((input / 100f) * canvasSize);
    }






    // Getters / setters
    public int getLocationScaleRatio() {
        return locationScaleRatio;
    }

    public void setLocationScaleRatio(int locationScaleRatio) {
        this.locationScaleRatio = locationScaleRatio;
    }

    public boolean isPortrait() {
        return isPortrait;
    }

    public boolean isTopLeftOrigin() {
        return isTopLeftOrigin;
    }
}
