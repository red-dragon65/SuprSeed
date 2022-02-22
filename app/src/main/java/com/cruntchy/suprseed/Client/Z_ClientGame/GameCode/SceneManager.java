package com.cruntchy.suprseed.Client.Z_ClientGame.GameCode;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneRunner;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;

public class SceneManager extends GameView {


    // TODO: This needs to handle loading, de-allocating, and running other scenes


    private SceneRunner someScene;


    /**
     * Constructor
     *
     * @param context
     * @param resources
     * @param gameData
     * @param touchHandler
     * @param loopRunner
     * @param renderer
     */
    public SceneManager(Context context, Resources resources, SharedPreferences gameData, TouchMethod touchHandler, RunnableConfig<GameView> loopRunner, RenderHandler renderer) {
        super(context, resources, gameData, touchHandler, loopRunner, renderer);
    }





    @Override
    public void initStartingState() {

        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene

        someScene = new SomeScene();

    }

    @Override
    public void logicLoop() {

        // Call run???

        someScene.logicLoop();
    }

    @Override
    public void drawingLoop() {

        // Call draw???

        someScene.drawingLoop();
    }
}
