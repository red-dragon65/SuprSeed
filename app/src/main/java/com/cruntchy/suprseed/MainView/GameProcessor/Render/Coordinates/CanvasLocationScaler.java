package com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LogicRates;

public class CanvasLocationScaler implements LocationScaler {


    // Used to calculate the correct position of a sprites location
    // Ensures that varying refresh rates and tick rates keep consistent
    // sprite movement

    // 1 assumes that refresh rate and logic rate are both at 60
    private float locationScaleRatio = 1;
    private float targetLogicRate = 60;


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
        loc[0] *= locationScaleRatio;
        loc[1] *= locationScaleRatio;

        return loc;
    }



    // Setters
    @Override
    public void setLocationScaleRatio(LogicRates logicRate) {

        this.locationScaleRatio = targetLogicRate / logicRate.getTickRate();
    }
}
