package com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LogicRates;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.RefreshTypes;

public interface LocationScaler {

    public float formatCoordinateToCanvas(float input, float canvasSize);

    public float[] applyLocationScale(float[] loc);

    public void setLocationScaleRatio(LogicRates logicRate);

}
