package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

public interface LocationHandler {


    float[] parseLocation(float[] loc);

    boolean isTopLeftOrigin();

    void setTopLeftOrigin(boolean value);

    float[] canvasCenter(float[] loc);

}
