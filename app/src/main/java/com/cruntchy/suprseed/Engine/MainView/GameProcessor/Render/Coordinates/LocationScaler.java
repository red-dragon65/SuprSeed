package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LogicRates;

public interface LocationScaler {

    float[] applyLocationScale(float[] loc);

    void setLocationScaleRatio(LogicRates logicRate);

}
