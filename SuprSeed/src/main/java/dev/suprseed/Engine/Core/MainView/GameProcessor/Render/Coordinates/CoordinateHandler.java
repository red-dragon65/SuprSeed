package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface CoordinateHandler {

    float[] parseLocation(Sprite sprite);
}
