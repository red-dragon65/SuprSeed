package com.cruntchy.suprseed.Engine.MainView.GameViewBuilder;

import com.cruntchy.suprseed.Engine.AssetLoader.ImageTransformer;
import com.cruntchy.suprseed.Engine.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.CanvasConfig;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.LoopConfig;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.ViewConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;


// Returns the default instantiated objects used for configuring the engine
public interface InfoBuilder<T> {

    // TODO: Finish me!

    DeviceSensor<T> getDefaultSensor();

    RunnableConfig<GameView> getDefaultRunnableConfig();

    LocationScaler getDefaultLocationScaler();

    LocationHandler getDefaultLocationHandler();

    RenderHandler getDefaultRenderProcessor();

    CoordinateHandler getDefaultCoordinateHandler();

    ImageTransformer getDefaultTransformer();

    LoopConfig getDefaultLoopConfig();

    ViewConfig getDefaultViewConfig();

    CanvasConfig getDefaultCanvasConfig();

}
