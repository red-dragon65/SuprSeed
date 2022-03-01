package com.cruntchy.suprseed.Engine.MainView.GameViewBuilder;

import com.cruntchy.suprseed.Engine.AssetLoader.ImageTransformer;
import com.cruntchy.suprseed.Engine.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;


// Returns the default instantiated objects used for configuring the engine
public interface InfoBuilder <T>{

    // TODO: Finish me!

    public DeviceSensor<T> getDefaultSensor();

    public TouchMethod getDefaultTouchMethod();

    public RunnableConfig<GameView> getDefaultRunnableConfig();
    public LocationScaler getDefaultLocationScaler();
    public LocationHandler getDefaultLocationHandler();

    public RenderHandler getDefaultRenderProcessor();
    public CoordinateHandler getDefaultCoordinateHandler();


    public ImageTransformer getDefaultTransformer();


}
