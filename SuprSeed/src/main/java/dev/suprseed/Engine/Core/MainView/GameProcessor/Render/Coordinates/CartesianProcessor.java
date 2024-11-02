package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.Engine.Core.MainView.EngineSettings.BaseConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;

public class CartesianProcessor implements LocationHandler {

    private boolean isTopLeftOrigin;


    // Constructor
    public CartesianProcessor(BaseConfig<LocationHandler> config) {

        config.applySettings(this);
    }


    @Override
    public float[] parseLocation(float[] loc) {

        // Set center origin if necessary
        if (!isTopLeftOrigin) {

            loc = canvasCenter(loc);
        }

        return loc;
    }


    // Returns the coordinates for the center of the canvas
    @Override
    public float[] canvasCenter(float[] loc) {

        // Offset x to center
        float middle_x = CanvasData.getInstance().getOriginalWidth() / 2;
        loc[0] += middle_x;

        // Offset y to center
        float middle_y = CanvasData.getInstance().getOriginalWidth() / 2;
        loc[1] -= middle_y;

        return loc;
    }

    @Override
    public boolean isTopLeftOrigin() {
        return isTopLeftOrigin;
    }

    // Getters/setters
    @Override
    public void setTopLeftOrigin(boolean enableTopLeftOrigin) {
        isTopLeftOrigin = enableTopLeftOrigin;
    }

}
