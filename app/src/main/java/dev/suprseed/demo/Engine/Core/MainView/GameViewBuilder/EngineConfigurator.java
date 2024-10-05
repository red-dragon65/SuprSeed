package dev.suprseed.demo.Engine.Core.MainView.GameViewBuilder;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.demo.Engine.Core.MainView.EngineSettings.CanvasConfig;
import dev.suprseed.demo.Engine.Core.MainView.EngineSettings.LoopConfig;
import dev.suprseed.demo.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.GameView;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.RefreshTypes;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.RunnableConfig;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.CartesianHandler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderProcessor;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.demo.Engine.Lib.AssetLoader.ImageProcessor;
import dev.suprseed.demo.Engine.Lib.AssetLoader.ImageTransformer;

public class EngineConfigurator extends BaseEngineConfigurator {

    private final RootScene rootScene;
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
    public EngineConfigurator(BaseEngineConfigurator configurator, RootScene rootScene) {
        super(configurator.context);

        this.rootScene = rootScene;
    }

    @Override
    public View buildView() {

        return new GameView(context, getLoopManager(), getRenderProcessor(), rootScene);
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
