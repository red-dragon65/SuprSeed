package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LogicRates;

public interface LocationScaler {

    public float formatCoordinateToCanvas(float input, float canvasSize);

    public float[] applyLocationScale(float[] loc);

    public void setLocationScaleRatio(LogicRates logicRate);

}
