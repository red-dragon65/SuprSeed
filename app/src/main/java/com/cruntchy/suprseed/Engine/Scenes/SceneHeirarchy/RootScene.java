package com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.core.graphics.drawable.IconCompat;

import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderSystem;

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
