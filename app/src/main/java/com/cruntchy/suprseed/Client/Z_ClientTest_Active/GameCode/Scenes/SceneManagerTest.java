package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene.MainScene;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.Engine.MainView.Scenes.Scene;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneController;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneChangeStrategy;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerTest implements SceneController {


    private final List<Scene> myScenes = new ArrayList<>();

    private Context context;
    private Resources res;
    private SharedPreferences gameData;

    // Constructor
    public SceneManagerTest() {

    }


    @Override
    public void initStartingState(Context context, Resources res, SharedPreferences gameData) {

        this.context = context;
        this.res = res;
        this.gameData = gameData;


        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene


        // Add the input listener here
        InputManager.getInstance().processorRegister.registerObject(new TouchHandler());


        // Add starting scenes here
        myScenes.add(new MainScene(this, "MainScene", context));


        // Activate starting scenes
        myScenes.get(0).setActive(true);
    }




    // Used by a scene to change to another scene using the specified scenes id
    @Override
    public void changeScene(SceneChangeStrategy strategy, Scene oldScene, String... sceneId) {

        strategy.changeScene(this, oldScene, sceneId);
    }


    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Resources getResources() {
        return res;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return gameData;
    }

    @Override
    public List<Scene> getScenes() {
        return myScenes;
    }





    @Override
    public void registerObject(Scene object) {

        myScenes.add(object);
    }

    @Override
    public void removeObject(Scene object) {

        myScenes.remove(object);
    }

    @Override
    public void removeAllObjects() {

        myScenes.clear();
    }
}
