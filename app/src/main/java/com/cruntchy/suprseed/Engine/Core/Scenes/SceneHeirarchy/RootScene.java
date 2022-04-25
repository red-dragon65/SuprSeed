package com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import com.cruntchy.suprseed.Engine.Core.System.LogicSystem;
import com.cruntchy.suprseed.Engine.Core.System.RenderSystem;

public abstract class RootScene extends SceneManager {

    public RootScene(Context context) {
        super(null, "root", context);

        // Register the top level scene to the system managers
        LogicSystem.getInstance().registerObject(this);
        RenderSystem.getInstance().imageRegister.registerObject(this);
        RenderSystem.getInstance().animationRegister.registerObject(this);
    }

    public abstract void initStartingState(Context context);
}
