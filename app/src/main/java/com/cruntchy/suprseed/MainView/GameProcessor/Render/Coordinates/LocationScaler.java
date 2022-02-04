package com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates;

public interface LocationScaler {

    public float formatCoordinateToCanvas(float input, float canvasSize);

    public float[] applyLocationScale(float[] loc);

    public void setLocationScaleRatio(float locationScaleRatio);

}
