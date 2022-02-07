package com.cruntchy.suprseed.Z_ClientGame.EngineSetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LoopConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderProcessor;

public class SceneManager extends GameView {



    // TODO: Make this extend the sceneHandler class
    //  This needs to handle loading, de-allocating, and running other scenes


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



    }

    @Override
    public void logicLoop() {

    }

    @Override
    public void drawingLoop() {


    }
}
