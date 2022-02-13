package com.cruntchy.suprseed.MainView.GameViewBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LogicRates;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LoopConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.RefreshTypes;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CanvasLocationScaler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderProcessor;
import com.cruntchy.suprseed.Z_ClientGame.EngineSetup.SceneManager;

public class DefaultEngineBuilder extends BaseEngineBuilder implements InfoBuilder<Float[]>{


    // Constructor
    public DefaultEngineBuilder(Context context, Resources res, SharedPreferences gameData){
        super(context, res, gameData);
    }


    @Override
    public GameView getView() {

        // TODO: Initialize the game view

        GameView defaultView = new SceneManager(context, res, gameData, getDefaultTouchMethod(), getDefaultRunnableConfig(), getDefaultRenderProcessor());

        return defaultView;
    }


    @Override
    public void setWindowConfig(AppCompatActivity mainActivity) {

        WindowConfigurator defaultWindowConfig = new DefaultWindowConfig(false, false);

        defaultWindowConfig.applyWindowSettings(mainActivity);
    }








    /*
    Returns the default objects used to build the default engine
     */

    @Override
    public DeviceSensor<Float[]> getDefaultSensor() {

        // TODO: Initialize the default modules state

        return null;
    }

    @Override
    public TouchMethod getDefaultTouchMethod() {

        // TODO: Initialize the default modules state

        return new TouchHandler();
    }


    @Override
    public RunnableConfig<GameView> getDefaultRunnableConfig(){

        RunnableConfig<GameView> loopConfig = new LoopConfig(RefreshTypes.SIXTY_FPS, LogicRates.SIXTY_TICKS, getDefaultLocationScaler());

        return loopConfig;
    }

    @Override
    public LocationScaler getDefaultLocationScaler(){

        return new CanvasLocationScaler();
    }



    @Override
    public RenderHandler getDefaultRenderProcessor(){

        return new RenderProcessor(getDefaultCoordinateHandler());
    }

    @Override
    public CoordinateHandler getDefaultCoordinateHandler(){

        return new CoordinateProcessor();
    }









}
