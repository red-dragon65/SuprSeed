package com.cruntchy.suprseed.Engine.Images;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;

public interface SpriteImage extends ImageType, Scalable {

    LocationTemporalScaler scaler = new LocationTemporalScaler();
}
