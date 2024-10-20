package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;

public interface LocationScaler {

    float[] applyLocationScale(float[] loc);

    float getLocationScaleRatio();

    void setLocationScaleRatio(LogicRates logicRate);

}
