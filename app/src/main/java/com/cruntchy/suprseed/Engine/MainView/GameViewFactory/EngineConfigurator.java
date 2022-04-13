package com.cruntchy.suprseed.Engine.MainView.GameViewFactory;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.Engine.AssetLoader.ImageProcessor;
import com.cruntchy.suprseed.Engine.AssetLoader.ImageTransformer;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.CanvasConfig;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.LoopConfig;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.ViewConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LogicRates;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LoopManager;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RefreshTypes;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneController;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CartesianHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderProcessor;

public class EngineConfigurator extends BaseEngineConfigurator {

    private final SceneController rootScene;
    private RunnableConfig<GameView> loopManager;
    private LocationScaler locationTemporalScaler;
    private RenderHandler renderProcessor;
    private CoordinateHandler coordinateHandler;
    private LocationHandler locationHandler;
    private ImageTransformer imageTransformer;
    private LoopConfig loopConfig;
    private ViewConfig viewConfig;
    private CanvasConfig canvasConfig;


    // Constructor
    public EngineConfigurator(BaseEngineConfigurator configurator, SceneController rootScene) {
        super(configurator.context, configurator.res, configurator.gameData);

        this.rootScene = rootScene;
    }

    @Override
    public View buildView() {

        return new GameView(context, res, gameData, getLoopManager(), getRenderProcessor(), rootScene);
    }

    @Override
    public void setWindowConfig(AppCompatActivity mainActivity) {

        getViewConfig().applySettings(mainActivity);
    }



    /*

    User can change out default configuration here if needed.

     */

    public RunnableConfig<GameView> getLoopManager() {

        if (loopManager == null) {

            return new LoopManager(getLoopConfig(), getLocationTemporalScaler());
        }

        return loopManager;
    }

    public EngineConfigurator setLoopManager(RunnableConfig<GameView> loopManager) {
        this.loopManager = loopManager;
        return this;
    }

    public LocationScaler getLocationTemporalScaler() {

        if (locationTemporalScaler == null) {
            return new LocationTemporalScaler();
        }

        return locationTemporalScaler;
    }

    public EngineConfigurator setLocationTemporalScaler(LocationScaler locationTemporalScaler) {
        this.locationTemporalScaler = locationTemporalScaler;
        return this;
    }

    public RenderHandler getRenderProcessor() {

        if (renderProcessor == null) {
            return new RenderProcessor(getCoordinateHandler());
        }

        return renderProcessor;
    }

    public EngineConfigurator setRenderProcessor(RenderHandler renderProcessor) {
        this.renderProcessor = renderProcessor;
        return this;
    }

    public CoordinateHandler getCoordinateHandler() {

        if (coordinateHandler == null) {
            return new CoordinateProcessor(getLocationHandler(), getLocationTemporalScaler());
        }

        return coordinateHandler;
    }

    public EngineConfigurator setCoordinateHandler(CoordinateHandler coordinateHandler) {
        this.coordinateHandler = coordinateHandler;
        return this;
    }

    public LocationHandler getLocationHandler() {

        if (locationHandler == null) {
            return new CartesianHandler(getCanvasConfig());
        }

        return locationHandler;
    }










    /*

    Returns the users configuration if possible to the 'viewBuilder' method.
    Otherwise, the default configuration is used where needed.

    The user can also retrieve default configs here if they wish to create their own partial builder.
     */

    public EngineConfigurator setLocationHandler(LocationHandler locationHandler) {
        this.locationHandler = locationHandler;
        return this;
    }

    public ImageTransformer getImageTransformer() {

        if (imageTransformer == null) {
            return new ImageProcessor();
        }

        return imageTransformer;
    }

    public EngineConfigurator setImageTransformer(ImageTransformer imageTransformer) {
        this.imageTransformer = imageTransformer;
        return this;
    }

    public LoopConfig getLoopConfig() {

        if (loopConfig == null) {
            return new LoopConfig(RefreshTypes.SIXTY_FPS, LogicRates.SIXTY_TICKS);
        }

        return loopConfig;
    }

    public EngineConfigurator setLoopConfig(LoopConfig loopConfig) {
        this.loopConfig = loopConfig;
        return this;
    }

    public ViewConfig getViewConfig() {

        if (viewConfig == null) {
            return new ViewConfig(true, true, true, true);
        }

        return viewConfig;
    }

    public EngineConfigurator setViewConfig(ViewConfig viewConfig) {
        this.viewConfig = viewConfig;
        return this;
    }

    public CanvasConfig getCanvasConfig() {

        if (canvasConfig == null) {
            return new CanvasConfig(true);
        }

        return canvasConfig;
    }

    public EngineConfigurator setCanvasConfig(CanvasConfig canvasConfig) {
        this.canvasConfig = canvasConfig;
        return this;
    }
}
