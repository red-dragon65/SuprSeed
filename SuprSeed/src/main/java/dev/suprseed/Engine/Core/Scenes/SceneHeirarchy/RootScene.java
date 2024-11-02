package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class RootScene extends SceneManager {

    private static RootScene INSTANCE;

    /*
    Prevent the user from using this class. The engine already sets up this class
    and passes to the user in order to allow them to load their initial scene.
    There is no reason for the user to connect their scenes directly to the logic and render system.
     */
    private RootScene(Context context) {
        super(null, "root", context);

        // Register the top level scene to the system managers
        LogicSystem.getInstance().getLogicRegister().registerObject(this);
        RenderSystem.getInstance().getImageRegister().registerObject(this);
        RenderSystem.getInstance().getAnimationRegister().registerObject(this);
    }


    public static RootScene getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = new RootScene(context);
        } else {

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "There can only be one RootScene instance! The RootScene is already instantiated!");
        }

        return INSTANCE;
    }
}
