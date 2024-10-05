package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;

public interface LocationScaler {

    float[] applyLocationScale(float[] loc);

    void setLocationScaleRatio(LogicRates logicRate);

    float getLocationScaleRatio();

}
