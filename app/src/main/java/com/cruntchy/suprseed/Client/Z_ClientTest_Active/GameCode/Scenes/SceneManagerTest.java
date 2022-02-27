package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets.AssetScriptTest;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene.FallingScene;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.HomeScreen.LandingScene;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.ImageProcessor;
import com.cruntchy.suprseed.Engine.AssetLoader.ImageTransformer;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.TouchMethod;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.RunnableConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.SpriteSystem;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerTest extends GameView implements Logic {

    // VERIFY: Observer pattern


    private List<Scene> myScenes = new ArrayList<>();


    // Constructor
    public SceneManagerTest(Context context, Resources resources, SharedPreferences gameData,
                            TouchMethod touchHandler, RunnableConfig<GameView> loopRunner, RenderHandler renderer,
                            ImageTransformer imageProcessor) {
        super(context, resources, gameData, touchHandler, loopRunner, renderer, imageProcessor);


    }



    @Override
    public void initStartingState() {

        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene

        FolderParser localFolderParser = new LocalFolderParser(resources);
        Streamable localStreamer = new LocalImageFileStreamer(resources, imageProcessor);
        AssetLoader myAssets = new AssetScriptTest(localStreamer, localFolderParser);


        myScenes.add(new TestScene(this, "testScene", myAssets));


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
                SpriteSystem.getInstance().resetState();

                // Enable the new scene
                scene.setActive(true);
                scene.setHidden(false);

                break;
            }
        }
    }

}
