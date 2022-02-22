package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface RenderHandler {

    public void setCanvasSize(int width, int height);
    public void setCanvas(Canvas canvas);
    public void drawSprite(Sprite sprite);

    public CoordinateHandler getCoordinateHandler();
}
