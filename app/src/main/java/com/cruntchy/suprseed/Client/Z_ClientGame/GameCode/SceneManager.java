package com.cruntchy.suprseed.Client.Z_ClientGame.GameCode;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Engine.MainView.Scenes.Scene;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneController;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneChangeStrategy;

import java.util.List;

public class SceneManager implements SceneController {


    // TODO: This needs to handle loading, de-allocating, and running other scenes


    public SceneManager() {

    }


    @Override
    public void initStartingState(Context context, Resources res, SharedPreferences gameData) {

        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene

    }

    @Override
    public void changeScene(SceneChangeStrategy strategy, Scene oldScene, String... sceneId) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public Resources getResources() {
        return null;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return null;
    }

    @Override
    public List<Scene> getScenes() {
        return null;
    }

    @Override
    public void registerObject(Scene object) {

    }

    @Override
    public void removeObject(Scene object) {

    }

    @Override
    public void removeAllObjects() {

    }
}
