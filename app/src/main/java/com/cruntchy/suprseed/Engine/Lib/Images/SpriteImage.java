package com.cruntchy.suprseed.Engine.Lib.Images;

import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;

public interface SpriteImage extends ImageType, Scalable {

    LocationTemporalScaler scaler = new LocationTemporalScaler();
}
