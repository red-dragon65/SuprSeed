package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.EngineContext;

public class RootScene extends SceneManager {

    private static boolean initialized = false;

    /*
    Prevent the user from using this class. The engine already sets up this class
    and passes to the user in order to allow them to load their initial scene.
    There is no reason for the user to connect their scenes directly to the logic and render system.
     */
    protected RootScene() {
        super("root");

        if (initialized) {
            EngineContext.getLogger().logMessage(ErrorType.WARN, "There is already a root scene registered! Adding another root scene may cause issue to occur!");
        }

        // Register the top level scene to the system managers
        EngineContext.getLogicSystem().getLogicRegister().registerObject(this);
        EngineContext.getRenderSystem().getImageRegister().registerObject(this);
        EngineContext.getRenderSystem().getAnimationRegister().registerObject(this);
    }
}
