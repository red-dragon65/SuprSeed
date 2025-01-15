package dev.suprseed.Engine.Core.MainView.GameViewBuilder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.MainView.EngineSettings.CanvasConfig;
import dev.suprseed.Engine.Core.MainView.EngineSettings.LoopConfig;
import dev.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.GameView;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.InputHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopController;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunnable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CartesianProcessor;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.CollisionDrawable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderProcessor;

public class EngineConfigurator extends BaseEngineConfigurator {

    private final SceneStarter sceneStarter;
    private LoopRunnable<GameView> loopManager;
    private RenderHandler renderProcessor;
    private CoordinateHandler coordinateHandler;
    private LocationHandler locationHandler;
    private LoopConfig loopConfig;
    private ViewConfig viewConfig;
    private CanvasConfig canvasConfig;
    private InputHandler inputHandler;
    private CollisionDrawable collisionDiagnoser;

    // Constructor
    public EngineConfigurator(Context context, SceneStarter sceneStarter, InputHandler inputHandler, CollisionDrawable collisionDiagnoser) {
        super(context);

        this.sceneStarter = sceneStarter;
        this.inputHandler = inputHandler;
        this.collisionDiagnoser = collisionDiagnoser;
    }

    @Override
    public View buildView() {

        RefreshHandler refreshHandler = new LoopController<>(getLoopConfig(), getLoopManager());

        return new GameView(context, refreshHandler, getLoopManager(), getRenderProcessor(), sceneStarter, inputHandler);
    }

    @Override
    public void setWindowConfig(AppCompatActivity mainActivity) {

        getViewConfig().applySettings(mainActivity);
    }



    /*

    User can change out default configuration here if needed.

    Returns the users configuration if possible to the 'viewBuilder' method.
    Otherwise, the default configuration is used where needed.

    The user can also retrieve default configs here if they wish to create their own partial builder.
     */

    public LoopRunnable<GameView> getLoopManager() {

        if (loopManager == null) {

            loopManager = new LoopRunner();
        }

        return loopManager;
    }

    public EngineConfigurator setLoopManager(LoopRunnable<GameView> loopManager) {
        this.loopManager = loopManager;
        return this;
    }

    public RenderHandler getRenderProcessor() {

        if (renderProcessor == null) {
            return new RenderProcessor(getCoordinateHandler(), collisionDiagnoser);
        }

        return renderProcessor;
    }

    public EngineConfigurator setRenderProcessor(RenderHandler renderProcessor) {
        this.renderProcessor = renderProcessor;
        return this;
    }

    public CoordinateHandler getCoordinateHandler() {

        if (coordinateHandler == null) {
            return new CoordinateProcessor(getLocationHandler());
        }

        return coordinateHandler;
    }

    public EngineConfigurator setCoordinateHandler(CoordinateHandler coordinateHandler) {
        this.coordinateHandler = coordinateHandler;
        return this;
    }

    public LocationHandler getLocationHandler() {

        if (locationHandler == null) {
            return new CartesianProcessor(getCanvasConfig());
        }

        return locationHandler;
    }


    public EngineConfigurator setLocationHandler(LocationHandler locationHandler) {
        this.locationHandler = locationHandler;
        return this;
    }

    public LoopConfig getLoopConfig() {

        if (loopConfig == null) {
            loopConfig = new LoopConfig(LogicRates.ONE_TWENTY_TICKS, 1f);
        }

        return loopConfig;
    }

    public EngineConfigurator setLoopConfig(LoopConfig loopConfig) {
        this.loopConfig = loopConfig;
        return this;
    }

    public ViewConfig getViewConfig() {

        if (viewConfig == null) {
            ViewConfig defaultConfig = new ViewConfig(true, true, true, true);
            CanvasData.getInstance().setViewConfig(defaultConfig);
            return defaultConfig;
        }

        CanvasData.getInstance().setViewConfig(viewConfig);
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
