package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface RenderHandler {

    void drawSprite(Sprite sprite);

    CoordinateHandler getCoordinateHandler();

    Paint getPaint();

    Canvas getCanvas();

    void setCanvas(Canvas canvas);
}
