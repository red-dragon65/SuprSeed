package com.cruntchy.suprseed.MainView.GameProcessor.Loop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.cruntchy.suprseed.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderProcessor;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {


    // Dependencies
    protected TouchMethod touchHandler;
    protected RunnableConfig<GameView> loopRunner;
    protected RenderProcessor renderer;

    protected Context context;
    protected Resources resources;
    protected SharedPreferences gameData;



    /**
     * Constructor
     * @param context
     */
    public GameView(Context context, Resources resources, SharedPreferences gameData, TouchMethod touchHandler, LoopConfig loopRunner, RenderProcessor renderer) {
        super(context);

        this.context = context;
        this.resources = resources;
        this.gameData = gameData;

        // Dependency inject
        this.touchHandler = touchHandler;
        this.loopRunner = loopRunner;
        this.renderer = renderer;
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

        // Set canvas size
        renderer.setCanvasSize(w, h);

        // Initialize the assets
        // Initialize static game objects
        // Initialize a new game loop
        initStartingState();

        super.onSizeChanged(w, h, oldw, oldh);
    }





    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: Verify that this will actually work
        // Set the renderers canvas
        renderer.setCanvas(canvas);

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


        loopRunner.run(this);
    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        // Stop logic and drawing
        loopRunner.setHardPause(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        //TODO: Initialize the sensors?

        // Update the loop config
        loopRunner.initLoop(this);

        // Continue drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        // Continue drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        // Pause game drawing (in game pause)
        if(!hasWindowFocus){

            loopRunner.setSoftPause(true);
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Process touch input
        // Note: this runs regardless if the game loop is allowed to run
        touchHandler.processInput(event);

        return true;
    }
}
