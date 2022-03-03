package com.cruntchy.suprseed.Client.Z_ClientGame.GameCode;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;

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
    public void changeScene(Scene oldScene, String sceneId) {

    }
}
