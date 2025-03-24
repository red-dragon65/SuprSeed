package dev.suprseed.Engine.Core.MainView.GameViewBuilder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.CanvasConfig;
import dev.suprseed.Engine.Core.MainView.EngineSettings.LoopConfig;
import dev.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.GameView;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopController;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunnable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshController;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.VelocityScaler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.WindowEventRegistry;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.Camera;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CartesianProcessor;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderProcessor;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.ViewPort;
import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Collisions.CollisionDiagnosticsOverlay;
import dev.suprseed.Engine.Lib.Input.InputRegistryManager;

public class EngineConfigurator extends BaseEngineConfigurator {

    private final SceneStarter sceneStarter;
    private LoopRunnable loopManager;
    private RenderHandler renderProcessor;
    private RefreshHandler refreshHandler;
    private CoordinateHandler coordinateHandler;
    private LocationHandler locationHandler;
    private LoopConfig loopConfig;
    private ViewConfig viewConfig;
    private CanvasConfig canvasConfig;
    private InputRegistryManager inputRegistryManager;

    // Constructor
    public EngineConfigurator(Context context, SceneStarter sceneStarter) {
        super(context);

        this.sceneStarter = sceneStarter;

        inputRegistryManager = new InputRegistryManager();

        // Setup the engine context and tools
        buildEngineContextServices();
        buildEngineToolsServices();
    }

    protected void buildEngineContextServices() {

        EngineContext.setCentralLogger(new CentralLogger());
        EngineContext.setScreen(new Screen());
        EngineContext.setLogicSystem(new LogicSystem());
        EngineContext.setRenderSystem(new RenderSystem());
    }

    protected void buildEngineToolsServices() {

        EngineTools.setGlobalCamera(new Camera());
        EngineTools.setViewPort(new ViewPort());
        EngineTools.setVelocityScaler(new VelocityScaler());
        EngineTools.setInputManager(inputRegistryManager);
        EngineTools.setCollisionDrawer(new CollisionDiagnosticsOverlay(false));
        EngineTools.setWindowEventRegistry(new WindowEventRegistry());
        EngineTools.setLoopController(new LoopController());
    }

    @Override
    public View buildView() {

        return new GameView(context, getRefreshHandler(), getLoopManager(), getRenderProcessor(), sceneStarter, inputRegistryManager);
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


    /*
    ----------------------------- ANDROID VIEW CONFIGURATION -----------------------------
     */
    public ViewConfig getViewConfig() {

        if (viewConfig == null) {
            viewConfig = new ViewConfig(true, true, true, true);
        }

        EngineContext.getScreen().setViewConfig(viewConfig);
        return viewConfig;
    }

    public EngineConfigurator setViewConfig(ViewConfig viewConfig) {
        this.viewConfig = viewConfig;
        return this;
    }


    /*
    ----------------------------- LOOP CONFIGURATION -----------------------------
     */
    public LoopRunnable getLoopManager() {

        if (loopManager == null) {

            loopManager = new LoopRunner();
        }

        return loopManager;
    }

    public EngineConfigurator setLoopManager(LoopRunnable loopManager) {
        this.loopManager = loopManager;

        if (refreshHandler != null) {

            String message = "Error: a refreshHandler instance was already created with an existing loopRunnable!" +
                    "The refreshHandler will be re-initialized using the new passed in loopRunnable in order" +
                    "to maintain engine coherency!" +
                    "The loopRunnable should be specified before creating the refreshHandler!";

            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);

            refreshHandler = new RefreshController(getLoopConfig(), getLoopManager());
        }
        return this;
    }

    public RefreshHandler getRefreshHandler() {

        if (refreshHandler == null) {

            refreshHandler = new RefreshController(getLoopConfig(), getLoopManager());
        }

        return refreshHandler;
    }

    public EngineConfigurator setRefreshHandler(RefreshHandler refreshHandler) {
        this.refreshHandler = refreshHandler;
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




    /*
    ----------------------------- RENDER CONFIGURATION -----------------------------
     */

    public RenderHandler getRenderProcessor() {

        if (renderProcessor == null) {
            renderProcessor = new RenderProcessor(getCoordinateHandler());
        }

        return renderProcessor;
    }

    public EngineConfigurator setRenderProcessor(RenderHandler renderProcessor) {
        this.renderProcessor = renderProcessor;
        return this;
    }

    public CoordinateHandler getCoordinateHandler() {

        if (coordinateHandler == null) {
            coordinateHandler = new CoordinateProcessor(getLocationHandler());
        }

        return coordinateHandler;
    }

    public EngineConfigurator setCoordinateHandler(CoordinateHandler coordinateHandler) {
        this.coordinateHandler = coordinateHandler;
        return this;
    }

    public LocationHandler getLocationHandler() {

        if (locationHandler == null) {
            locationHandler = new CartesianProcessor(getCanvasConfig());
        }

        return locationHandler;
    }

    public EngineConfigurator setLocationHandler(LocationHandler locationHandler) {
        this.locationHandler = locationHandler;
        return this;
    }

    public CanvasConfig getCanvasConfig() {

        if (canvasConfig == null) {
            canvasConfig = new CanvasConfig(true);
        }

        return canvasConfig;
    }

    public EngineConfigurator setCanvasConfig(CanvasConfig canvasConfig) {
        this.canvasConfig = canvasConfig;
        return this;
    }

}
