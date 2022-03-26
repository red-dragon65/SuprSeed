package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets.AssetScriptTest;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene.MainScene;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerTest implements Logic, SceneController {

    // VERIFY: Observer pattern


    private final List<Scene> myScenes = new ArrayList<>();


    // Constructor
    public SceneManagerTest() {

    }


    @Override
    public void initStartingState(Context context, Resources res, SharedPreferences gameData) {

        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene

        FolderParser localFolderParser = new LocalFolderParser(res);
        Streamable localStreamer = new LocalImageFileStreamer(res);
        AssetLoader myAssets = new AssetScriptTest(localStreamer, localFolderParser);


        // Add the input listener here
        InputManager.getInstance().addInputHandler(new TouchHandler());


        // Add scenes here
        myScenes.add(new MainScene(this, "MainScene", myAssets));


        // Activate start scene
        myScenes.get(0).setActive(true);


        // Register this scenes logic
        //spriteSystem.registerLogicSprite(this);
    }




    @Override
    public void runLogic() {

        // We don't need this for now...
    }





    // Used by a scene to change to another scene using the specified scenes id
    @Override
    public void changeScene(Scene oldScene, String sceneId) {

        for(Scene scene : myScenes){

            if(scene.getId().equals(sceneId)){

                // Disable the old scene
                oldScene.setActive(false);
                oldScene.setHidden(true);

                // Clear the system register
                LogicSystem.getInstance().resetState();

                // Enable the new scene
                scene.setActive(true);
                scene.setHidden(false);

                break;
            }
        }
    }

}
