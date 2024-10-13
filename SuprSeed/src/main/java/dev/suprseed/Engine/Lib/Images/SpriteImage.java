package dev.suprseed.Engine.Lib.Images;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;

public interface SpriteImage extends ImageType, Scalable {

    LocationTemporalScaler scaler = new LocationTemporalScaler();
    String getTag();
}
