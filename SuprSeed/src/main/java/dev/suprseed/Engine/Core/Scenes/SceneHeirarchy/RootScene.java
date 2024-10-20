package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public abstract class RootScene extends SceneManager {

    public RootScene(Context context) {
        super(null, "root", context);

        // Register the top level scene to the system managers
        LogicSystem.getInstance().getLogicRegister().registerObject(this);
        RenderSystem.getInstance().getImageRegister().registerObject(this);
        RenderSystem.getInstance().getAnimationRegister().registerObject(this);
    }

    public abstract void initStartingState(Context context);
}
