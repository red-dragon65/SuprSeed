package com.cruntchy.suprseed.MainView.GameProcessor;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.cruntchy.suprseed.InputHandler.TouchInput.TouchMethod;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {


    // Dependencies
    protected TouchMethod touchHandler;

    /*
    ProcessHandler

    GameRenderer
        GameScaler
        GameCameraView

    GamePhysicsHandler

     */

    protected Context context;
    protected Resources resources;
    protected SharedPreferences gameData;



    /**
     * Constructor
     * @param context
     */
    public GameView(Context context, Resources resources, SharedPreferences gameData, TouchMethod touchHandler) {
        super(context);

        this.context = context;
        this.resources = resources;
        this.gameData = gameData;

        // Dependency inject
        this.touchHandler = touchHandler;
    }




    // Instantiates game objects to their initial state
    abstract void initStartingState();

    // Actually run the game
    abstract void logicLoop();
    abstract void drawingLoop();

    /*
    // Reset game objects to their original state
    reset()?

    // Used for loading in new sprites for things like level changes
    loadNewState()?
     */





    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        // Initialize the assets
        // Initialize static game objects
        // Initialize a new game loop
        initStartingState();

        super.onSizeChanged(w, h, oldw, oldh);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        Game Renderer
        --------------
        Set refresh speed
        - Only for higher APIs
        - Can be set to 60fps or 120fps
        - Handles asset scaling???
        - Handles drawing

        Physics Handler
        ----------------
        Set logic rate
        - Can be set to 30, 60, 120, or 240 ticks
        - Scales drawing location (makes setting sprite location the same despite number of ticks)
        - Converts coordinate rotation


        This class
        -----------
        Call users physics
        - GameLoop.logicLoop();

        Call user drawing
        - GameLoop.drawingLoop();

         */

    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        // Stop the game loop
        // Stop the game drawing (in game pause)

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        // Initialize the sensors?

        // Resume the game loop
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        // Resume the game loop
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        // Pause game drawing (in game pause)
    }











    @Override
    public boolean onTouchEvent(MotionEvent event) {

        touchHandler.processInput(event);

        return true;
    }



}
