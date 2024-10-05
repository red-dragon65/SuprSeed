package dev.suprseed.demo.Engine.Lib.Images;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;

public interface SpriteImage extends ImageType, Scalable {

    LocationTemporalScaler scaler = new LocationTemporalScaler();
}
