package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface RenderHandler {

    void setCanvas(Canvas canvas);

    void drawSprite(Sprite sprite);

    CoordinateHandler getCoordinateHandler();
}
