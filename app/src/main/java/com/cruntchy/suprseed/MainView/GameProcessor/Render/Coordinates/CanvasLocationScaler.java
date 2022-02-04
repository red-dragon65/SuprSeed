package com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates;

public class CanvasLocationScaler implements LocationScaler {


    // Used to calculate the correct position of a sprites location
    // Ensures that varying refresh rates and tick rates keep consistent
    // sprite movement
    private float locationScaleRatio = 1;



    @Override
    public float formatCoordinateToCanvas(float input, float canvasSize){

        // Converts a given value to scale to the same point on a given canvas
        /*
        Example: sprite has x set to 25. This will be converted to (canvas width * 0.25).
         */

        return ((input / 100f) * canvasSize);
    }


    @Override
    public float[] applyLocationScale(float[] loc){

        // Add multiplier to scale location to varying refresh/tick rates
        loc[0] = loc[0] * locationScaleRatio;
        loc[1] = loc[1] * locationScaleRatio;

        return loc;
    }



    // Setters
    @Override
    public void setLocationScaleRatio(float locationScaleRatio) {
        this.locationScaleRatio = locationScaleRatio;
    }
}
