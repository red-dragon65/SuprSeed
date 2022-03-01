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

    private Paint paint = new Paint();

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

        // Get the latest canvas from onDraw
        this.canvas = canvas;
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


        // Actually draw sprite
        if(sprite.isEnabled() && sprite.isShow()){

            // Get drawing location of sprite
            float[] finalLoc = coordinateHandler.parseLocation(sprite, originalCanvasWidth, originalCanvasHeight);

            // Draw the sprite at the final location
            canvas.drawBitmap(
                    sprite.getImageHandler().getSelectedImageSet().getImage(),
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
