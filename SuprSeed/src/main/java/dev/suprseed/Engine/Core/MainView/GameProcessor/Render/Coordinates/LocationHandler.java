package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

public interface LocationHandler {


    float[] parseLocation(float[] loc);

    boolean isTopLeftOrigin();

    void setTopLeftOrigin(boolean value);

    float[] canvasCenter(float[] loc);

}
