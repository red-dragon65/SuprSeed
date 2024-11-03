package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class RootScene extends SceneManager {

    private static boolean initialized = false;

    /*
    Prevent the user from using this class. The engine already sets up this class
    and passes to the user in order to allow them to load their initial scene.
    There is no reason for the user to connect their scenes directly to the logic and render system.
     */
    protected RootScene(Context context) {
        super(null, "root");
        this.context = context;

        if (initialized) {
            CentralLogger.getInstance().logMessage(ErrorType.WARN, "There is already a root scene registered! Adding another root scene may cause issue to occur!");
        }

        // Register the top level scene to the system managers
        LogicSystem.getInstance().getLogicRegister().registerObject(this);
        RenderSystem.getInstance().getImageRegister().registerObject(this);
        RenderSystem.getInstance().getAnimationRegister().registerObject(this);
    }
}
