package com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates;

public interface LocationHandler {


    public float[] parseLocation(float[] loc);

    public boolean isPortrait();
    public boolean isTopLeftOrigin();

    public float[] canvasCenter(float[] loc);

    public float getCanvasWidth();
    public float getCanvasHeight();

}
