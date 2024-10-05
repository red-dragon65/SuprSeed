package dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface RenderHandler {

    void setCanvas(Canvas canvas);

    void drawSprite(Sprite sprite);

    CoordinateHandler getCoordinateHandler();

    Paint getPaint();

    Canvas getCanvas();
}
