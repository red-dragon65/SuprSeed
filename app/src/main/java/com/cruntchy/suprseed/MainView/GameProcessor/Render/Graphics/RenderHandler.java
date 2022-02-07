package com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;

import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;

public interface RenderHandler {

    public void setCanvasSize(int width, int height);
    public void setCanvas(Canvas canvas);
    public void drawSprite(/*Sprite sprite*/);

    public CoordinateHandler getCoordinateHandler();
}
