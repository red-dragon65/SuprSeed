package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class RenderProcessor implements RenderHandler {

    // The hardware accelerated canvas provided by a view
    private Canvas canvas;

    // TODO: Get the paint object
    private Paint paint;

    // Dependencies
    private CoordinateHandler coordinateHandler;


    // The original size of the screen
    private float originalCanvasWidth = 0;
    private float originalCanvasHeight = 0;



    // Constructor
    public RenderProcessor(CoordinateHandler coordinateHandler){

        // Dependency injection
        this.coordinateHandler = coordinateHandler;
    }



    @Override
    public void setCanvasSize(int w, int h){

        originalCanvasWidth = w;
        originalCanvasHeight = h;

        CentralLogger.logMessage(ErrorType.INFO, "The canvas dimensions have been set...");
    }


    @Override
    public void setCanvas(Canvas canvas){

        // Set the canvas if it is null
        if(this.canvas == null){

            this.canvas = canvas;

            CentralLogger.logMessage(ErrorType.INFO, "The canvas has been set...");
        }
    }


    @Override
    public void drawSprite(Sprite sprite){

        // Show warning if canvas is not set
        if(this.canvas == null){

            CentralLogger.logMessage(ErrorType.WARNING, "The canvas has not been initialized!");

            return;
        }

        if(this.originalCanvasWidth == 0 || this.originalCanvasHeight == 0){

            CentralLogger.logMessage(ErrorType.WARNING, "The canvas dimensions have not been initialized!!");

            return;
        }

        if(sprite.isEnabled() && sprite.isShow()){

            float[] spriteLoc = {sprite.getX(), sprite.getY()};

            // Get drawing location of sprite
            float[] finalLoc = coordinateHandler.parseLocation(spriteLoc);

            // Draw the sprite at the final location
            canvas.drawBitmap(
                    sprite.getImageHandler().getSelectedImage().getImage(),
                    finalLoc[0],
                    finalLoc[1],
                    paint
            );
        }

    }


    @Override
    public CoordinateHandler getCoordinateHandler(){
        return coordinateHandler;
    }



}
