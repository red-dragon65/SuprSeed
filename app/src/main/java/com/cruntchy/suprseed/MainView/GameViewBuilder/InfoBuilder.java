package com.cruntchy.suprseed.MainView.GameViewBuilder;

import com.cruntchy.suprseed.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.LoopConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.Render.Graphics.RenderProcessor;


// Returns the default instantiated objects used for configuring the engine
public interface InfoBuilder <T>{

    // TODO: Finish me!

    public DeviceSensor<T> getDefaultSensor();

    public TouchMethod getDefaultTouchMethod();

    public RunnableConfig<GameView> getDefaultRunnableConfig();
    public LocationScaler getDefaultLocationScaler();

    public RenderHandler getDefaultRenderProcessor();
    public CoordinateHandler getDefaultCoordinateHandler();


}
