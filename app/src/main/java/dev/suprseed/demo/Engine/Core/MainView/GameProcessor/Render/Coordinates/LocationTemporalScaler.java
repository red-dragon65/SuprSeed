package dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.LogicRates;

public class LocationTemporalScaler implements LocationScaler {


    // Used to calculate the correct position of a sprites location
    // Ensures that varying refresh rates and tick rates keep consistent
    // sprite movement

    // 1 assumes that refresh rate and logic rate are both at 60

    private static final float targetLogicRate = 60;
    // Enforce only one setting across instance usages
    private static float locationScaleRatio = 1;


    @Override
    public float[] applyLocationScale(float[] loc) {

        // Add multiplier to scale location to varying refresh/tick rates
        loc[0] *= locationScaleRatio;
        loc[1] *= locationScaleRatio;

        return loc;
    }

    // Setters
    @Override
    public void setLocationScaleRatio(LogicRates logicRate) {

        locationScaleRatio = targetLogicRate / logicRate.getTickRate();
    }

    @Override
    public float getLocationScaleRatio() {
        return locationScaleRatio;
    }
}
