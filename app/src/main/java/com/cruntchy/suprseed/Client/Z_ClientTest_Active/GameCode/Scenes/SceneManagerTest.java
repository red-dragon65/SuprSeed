package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene.MainScene;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.BaseScene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.RootScene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.SceneManager;
import com.cruntchy.suprseed.Engine.MainView.Scenes.GameInfo;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderSystem;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerTest extends SceneManager implements RootScene {

    // Constructor
    public SceneManagerTest() {
        super(null, "root");

        LogicSystem.getInstance().registerObject(this);
        RenderSystem.getInstance().imageRegister.registerObject(this);
        RenderSystem.getInstance().animationRegister.registerObject(this);
    }


    @Override
    public void initStartingState(Context context, Resources res, SharedPreferences gameData) {

        gameInfo = new GameInfo(context, res, gameData);


        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene


        // Add the input listener here
        InputManager.getInstance().processorRegister.registerObject(new TouchHandler());


        // Add starting scenes/all scenes here
        BaseScene startingScene = new MainScene(this, "MainScene");

        sceneRegister.registerObject(startingScene);
    }

}
