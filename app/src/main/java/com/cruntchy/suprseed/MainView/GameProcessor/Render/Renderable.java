package com.cruntchy.suprseed.MainView.GameProcessor.Render;

import android.graphics.Canvas;

public interface Renderable {

    public void setCanvasSize(int width, int height);
    public void setCanvas(Canvas canvas);
    public void drawSprite(/*Sprite sprite*/);

    public Camera getCamera();
    public CoordinateHandler getCoordinateHandler();
}
