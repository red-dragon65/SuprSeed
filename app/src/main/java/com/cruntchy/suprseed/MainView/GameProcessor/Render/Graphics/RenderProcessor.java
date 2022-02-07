package com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;

import com.cruntchy.suprseed.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;

public class RenderProcessor implements RenderHandler {

    // The hardware accelerated canvas provided by a view
    private Canvas canvas;

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
    public void drawSprite(/*ImageSprite sprite*/){

        // Show warning if canvas is not set
        if(this.canvas == null){

            CentralLogger.logMessage(ErrorType.WARNING, "The canvas has not been initialized!");

            return;
        }

        if(this.originalCanvasWidth == 0 || this.originalCanvasHeight == 0){

            CentralLogger.logMessage(ErrorType.WARNING, "The canvas dimensions have not been initialized!!");

            return;
        }


        // TODO: Draw a sprite here
        /*

        float[] spriteLoc = {sprite.getX(), sprite.getY()};

        // Get drawing location of sprite
        float[] finalLoc = coordinateHandler.parseFinalLocation(spriteLoc);

        // Draw the sprite at the final location
        canvas.drawBitmap(
            sprite.getImage(),
            finalLoc[0],
            finalLoc[1],
            paint
        );*/
    }


    @Override
    public CoordinateHandler getCoordinateHandler(){
        return coordinateHandler;
    }



}
