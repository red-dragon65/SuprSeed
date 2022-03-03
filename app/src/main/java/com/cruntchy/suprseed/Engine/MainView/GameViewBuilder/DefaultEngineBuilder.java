package com.cruntchy.suprseed.Engine.MainView.GameViewBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.SceneManagerTest;
import com.cruntchy.suprseed.Engine.AssetLoader.ImageProcessor;
import com.cruntchy.suprseed.Engine.AssetLoader.ImageTransformer;
import com.cruntchy.suprseed.Engine.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LogicRates;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LoopConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RefreshTypes;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CartesianHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderProcessor;

public class DefaultEngineBuilder extends BaseEngineBuilder implements InfoBuilder<Float[]>{


    private final LocationScaler locationScaler = new LocationTemporalScaler();



    // Constructor
    public DefaultEngineBuilder(Context context, Resources res, SharedPreferences gameData){
        super(context, res, gameData);
    }


    @Override
    public GameView getView() {

        // TODO: Initialize the game view

        SceneController testScene = new SceneManagerTest();

        GameView defaultView = new GameView(context, res, gameData, getDefaultTouchMethod(), getDefaultRunnableConfig(), getDefaultRenderProcessor(), testScene);

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

        return locationScaler;
    }



    @Override
    public RenderHandler getDefaultRenderProcessor(){

        return new RenderProcessor(getDefaultCoordinateHandler());
    }

    @Override
    public CoordinateHandler getDefaultCoordinateHandler(){

        return new CoordinateProcessor(getDefaultLocationHandler(), getDefaultLocationScaler());
    }

    @Override
    public LocationHandler getDefaultLocationHandler(){

        return new CartesianHandler(true, true);
    }


    @Override
    public ImageTransformer getDefaultTransformer(){

        return new ImageProcessor();
    }







}
