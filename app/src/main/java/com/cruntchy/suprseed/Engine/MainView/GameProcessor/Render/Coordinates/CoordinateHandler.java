package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface CoordinateHandler {

    public float[] parseLocation(Sprite sprite, float canvasWidth, float canvasHeight);
}
